package com.winterframework.firefrog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FhxToFhBatchConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(FhxToFhBatchConfig.class);
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	
	@Bean
    protected Step stepForNoticeFhxFinished() {
        return stepBuilderFactory
        		.get("stepForNoticeFhxFinished")
        		.tasklet(new Tasklet() {
		            @Override
		            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		                logger.info("fhx to fh finished.....");
		                return RepeatStatus.FINISHED;
		            }
        		}).build();
    }
	/**
	 * Job 執行點
	 * @return
	 */
	@Bean
	@Qualifier(value="fhxJob")
	public Job doFhxJob(){
		return jobBuilderFactory.get("doFhxJob")
				.start(stepForNoticeFhxFinished())
				.build();
	}

}
