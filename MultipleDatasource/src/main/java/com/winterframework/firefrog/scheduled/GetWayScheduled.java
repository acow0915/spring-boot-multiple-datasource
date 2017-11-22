package com.winterframework.firefrog.scheduled;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winterframework.firefrog.dao.FhPtGameBetRecordDao;

@Component
public class GetWayScheduled {
	
	private static final Logger logger = LoggerFactory.getLogger(GetWayScheduled.class);
	
	@Autowired
	private JobLauncher jobLauncher;

    @Autowired
    @Qualifier(value="ptJob")
    private Job ptJob;
    
    @Autowired
    @Qualifier(value="fhxJob")
    private Job fhxJob;
	
	@Autowired
	private FhPtGameBetRecordDao fhPtGameBetRecordDao;
	// 147967708
	@Scheduled(cron="0 0/1 * * * ?")
	public void doGetWay(){
		//先查出目前最大ID
		Long maxId = Optional.ofNullable(fhPtGameBetRecordDao.findMaxId())
							.orElse(146138784L);
		logger.info("-----------" + maxId + "-----------");
		
		JobParameters jobParameters = new JobParametersBuilder() 
                .addLong("lastTimeId", maxId)
                .toJobParameters(); 
		
		try {
			jobLauncher.run(ptJob, jobParameters);
			
			jobLauncher.run(fhxJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
