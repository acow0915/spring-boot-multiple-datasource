package com.winterframework.firefrog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EXP_CHANGE_TYPE")
public class ExpChangeType {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	/**變動分類名稱*/
	@Column(name = "TYPE_NAME")
	private String typeName;
	/**帳變大分類*/
	@Column(name = "SOURCE_TYPE")
	private String sourceType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
}
