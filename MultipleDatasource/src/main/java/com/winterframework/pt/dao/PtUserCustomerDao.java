package com.winterframework.pt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.winterframework.pt.entity.PtUserCustomer;

@Repository("ptUserCustomerDao")
public interface PtUserCustomerDao extends JpaRepository<PtUserCustomer, Long>{

	@Query(" SELECT p FROM PtUserCustomer p where p.id.ptAccount = :ptAccount")
	public PtUserCustomer findByPtAccount(@Param("ptAccount") String ptAccount);
}
