package com.winterframework.firefrog;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.winterframework.firefrog.config.FireFrogDataSourceConfig;
import com.winterframework.firefrog.config.H2DataSourceConfig;
import com.winterframework.firefrog.config.PtDataSourceConfig;
import com.winterframework.firefrog.dao.ExpChangeTypeDao;
import com.winterframework.firefrog.dao.FhPtGameBetRecordDao;
import com.winterframework.firefrog.entity.ExpChangeType;
import com.winterframework.pt.dao.PtGameBetRecordDao;
import com.winterframework.pt.entity.PtGameBetRecord;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import({GetWayMain.class,
	FireFrogDataSourceConfig.class,
	PtDataSourceConfig.class,
	H2DataSourceConfig.class})
public class TestApp {

	@Autowired
	private ExpChangeTypeDao expChangeTypeDao;
	
	@Autowired
//	@Qualifier("ptGameBetRecordDao")
	private PtGameBetRecordDao ptGameBetRecordDao;
	
	@Autowired
//	@Qualifier("fhPtGameBetRecordDao")
	private FhPtGameBetRecordDao fhPtGameBetRecordDao;
	
	@Test
    public void exampleTest() {
		List<ExpChangeType> expChangeTypes = expChangeTypeDao.findAll();
		System.out.println(expChangeTypes.size());
		
		//先查出目前最大ID
		Long maxId = Optional.ofNullable(fhPtGameBetRecordDao.findMaxId())
							.orElse(0L);
		
		List<PtGameBetRecord> ptGameBetRecord = ptGameBetRecordDao.findBySnAndPtAccount("291842125334", "FH_CHENGSHUAI12345");
		
		System.out.println(ptGameBetRecord.size());
    }
}
