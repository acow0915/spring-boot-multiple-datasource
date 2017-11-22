package com.winterframework.firefrog.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.orm.JpaNativeQueryProvider;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.winterframework.firefrog.DataPartitioner;
import com.winterframework.firefrog.entity.FhPtGameBetRecord;
import com.winterframework.pt.dao.PtUserCustomerDao;
import com.winterframework.pt.entity.PtGameBetRecord;
import com.winterframework.pt.entity.PtUserCustomer;

@Configuration
public class PtToFhBatchConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(PtToFhBatchConfig.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("ptEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean ptEntityManagerFactory;
	
	@Autowired
	@Qualifier("oracleEntityManagerFactory")
	private LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory;
	
	@Autowired
	@Qualifier("oracleTransactionManager")
	private PlatformTransactionManager oracleTransactionManager;
	
	@Autowired
	private PtUserCustomerDao ptUserCustomerDao;
	
	/**暫存 pt用戶資料, 避免反覆query*/
	private static Map<String, PtUserCustomer> cacheUserData = new HashMap<>();
	
	/**
	 * 讀取資料
	 * @param lastTimeId
	 * @param fromId
	 * @param toId
	 * @return
	 */
	@Bean(destroyMethod="")//因有設定scope, 這邊destroy要設定, 避免一值跳警告
	@StepScope
	public JpaPagingItemReader<PtGameBetRecord> reader(@Value("#{jobParameters['lastTimeId']}")Long lastTimeId,
			@Value("#{stepExecutionContext['fromId']}") Long fromId,
			@Value("#{stepExecutionContext['toId']}") Long toId) {
		if(null == lastTimeId){
			lastTimeId = 148016269L;
		}
		
	    JpaPagingItemReader<PtGameBetRecord> databaseReader = new JpaPagingItemReader<>();
	    databaseReader.setEntityManagerFactory(ptEntityManagerFactory.getObject());
        
        JpaNativeQueryProvider<PtGameBetRecord> queryProvider = new JpaNativeQueryProvider<PtGameBetRecord>();
        queryProvider.setSqlQuery("select * from PT_GAME_BET_RECORD where id >= :startId and id <= :endId ");
        queryProvider.setEntityClass(PtGameBetRecord.class);
        try {
			queryProvider.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
		}
        Map<String, Object> map = new HashMap<>();
        map.put("startId", lastTimeId + fromId);
        map.put("endId", lastTimeId + toId);
        
        databaseReader.setPageSize(3);
        databaseReader.setQueryProvider(queryProvider);
        databaseReader.setParameterValues(map);
        databaseReader.setSaveState(true);
        
	    return databaseReader;
	}
	
	@Bean(destroyMethod="")//因有設定scope, 這邊destroy要設定, 避免一值跳警告
	@StepScope
	public JpaItemWriter<FhPtGameBetRecord> writer() {
		JpaItemWriter<FhPtGameBetRecord> itemWriter = new JpaItemWriter<FhPtGameBetRecord>();
		itemWriter.setEntityManagerFactory(oracleEntityManagerFactory.getObject());
		return itemWriter;
	}
	
	/**
	 * 多工Step
	 * @return
	 */
	@Bean
	public Step partitionStep(){
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.afterPropertiesSet();
        
		return stepBuilderFactory.get("partitionStep")
				.partitioner("transForData", new DataPartitioner())
				.gridSize(10)
				.step(transForData())
				.taskExecutor(taskExecutor)
				.build();
	}
	
	/**
	 * 主要資料轉換Step
	 * @return
	 */
	@Bean
	public Step transForData() {
		return stepBuilderFactory.get("transForData")
				.transactionManager(oracleTransactionManager)
				.<PtGameBetRecord, FhPtGameBetRecord>chunk(200)
				.reader(reader(null, null, null))
				.processor(new ItemProcessor<PtGameBetRecord, FhPtGameBetRecord>() {
					@Override
					public FhPtGameBetRecord process(PtGameBetRecord readItem) throws Exception {
						final FhPtGameBetRecord processItem = new FhPtGameBetRecord();
						//轉換4.0 table
						BeanUtils.copyProperties(readItem, processItem);
						//取得與4.0用戶對應資料
						PtUserCustomer ptUserCustomer = Optional.ofNullable(cacheUserData.get(processItem.getPtAccount()))
														.orElseGet(() -> {
															PtUserCustomer userCustomer = ptUserCustomerDao.findByPtAccount(processItem.getPtAccount());
															cacheUserData.put(processItem.getPtAccount(), userCustomer);
															return userCustomer;
														});
						processItem.setAccount(ptUserCustomer.getFfAccount());
						processItem.setUserId(ptUserCustomer.getId().getFfId());
						
						logger.info("進行資料轉換 id : {}, ptAccount : {}, ffAccount : {}, ffId : {}",
								processItem.getId(), processItem.getPtAccount(), processItem.getAccount(), processItem.getUserId());
						return processItem;
					}
				})
				.writer(writer())
				.build();
	}
	
	@Bean
    protected Step stepForNoticePtFinished() {
        return stepBuilderFactory
        		.get("stepForNoticePtFinished")
        		.tasklet(new Tasklet() {
		            @Override
		            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		                logger.info("pt to fh finished.....");
		                return RepeatStatus.FINISHED;
		            }
        		}).build();
    }
	/**
	 * Job 執行點
	 * @return
	 */
	@Bean
	@Qualifier(value="ptJob")
	public Job doPtJob(){
		return jobBuilderFactory.get("doPtJob")
				.start(partitionStep())
				.next(stepForNoticePtFinished())
				.build();
	}
}
