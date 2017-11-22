package com.winterframework.firefrog.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.winterframework.firefrog.entity.FhPtGameBetRecord;

@Repository("fhPtGameBetRecordDao")
public interface FhPtGameBetRecordDao extends CrudRepository<FhPtGameBetRecord, Long> {

	@Query("select max(f.id) from FhPtGameBetRecord f")
	public Long findMaxId();
}
