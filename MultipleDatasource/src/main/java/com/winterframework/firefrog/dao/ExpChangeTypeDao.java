package com.winterframework.firefrog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winterframework.firefrog.entity.ExpChangeType;

@Repository(value="expChangeTypeDao")
public interface ExpChangeTypeDao extends JpaRepository<ExpChangeType, Long> {

	public List<ExpChangeType> findAll();
}
