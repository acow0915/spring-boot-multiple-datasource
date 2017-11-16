package com.winterframework.pt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winterframework.pt.entity.PtGameBetRecord;

@Repository("ptGameBetRecordDao")
public interface PtGameBetRecordDao extends JpaRepository<PtGameBetRecord, Long> {

	public PtGameBetRecord findById(Long id);
	
	public List<PtGameBetRecord> findBySnAndPtAccount(String sn, String ptAccount);
}
