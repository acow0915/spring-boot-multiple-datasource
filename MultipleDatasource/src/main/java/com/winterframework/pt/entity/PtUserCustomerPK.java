package com.winterframework.pt.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PT_USER_CUSTOMER database table.
 * 
 */
@Embeddable
public class PtUserCustomerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID")
	private Long id;

	@Column(name="PT_ACCOUNT")
	private String ptAccount;

	@Column(name="FF_ID")
	private Long ffId;

	public PtUserCustomerPK() {
	}
	
	public String getPtAccount() {
		return this.ptAccount;
	}
	public void setPtAccount(String ptAccount) {
		this.ptAccount = ptAccount;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFfId() {
		return ffId;
	}

	public void setFfId(Long ffId) {
		this.ffId = ffId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PtUserCustomerPK)) {
			return false;
		}
		PtUserCustomerPK castOther = (PtUserCustomerPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.ptAccount.equals(castOther.ptAccount)
			&& (this.ffId == castOther.ffId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.ptAccount.hashCode();
		hash = hash * prime + ((int) (this.ffId ^ (this.ffId >>> 32)));
		
		return hash;
	}
}