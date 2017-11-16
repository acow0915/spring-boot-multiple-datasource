package com.winterframework.firefrog;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.winterframework.firefrog.dao.ExpChangeTypeDao;
import com.winterframework.firefrog.entity.ExpChangeType;
import com.winterframework.pt.dao.PtGameBetRecordDao;
import com.winterframework.pt.entity.PtGameBetRecord;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(GetWayMain.class)
public class TestApp {

	@Autowired
	private ExpChangeTypeDao expChangeTypeDao;
	
	@Autowired
	private PtGameBetRecordDao ttGameBetRecordDao;
	
	@Test
    public void exampleTest() {
		List<ExpChangeType> expChangeTypes = expChangeTypeDao.findAll();
		System.out.println(expChangeTypes.size());
		
		List<PtGameBetRecord> ptGameBetRecord = ttGameBetRecordDao.findBySnAndPtAccount("291842125334", "FH_CHENGSHUAI12345");
		
		System.out.println(ptGameBetRecord.size());
    }
}
