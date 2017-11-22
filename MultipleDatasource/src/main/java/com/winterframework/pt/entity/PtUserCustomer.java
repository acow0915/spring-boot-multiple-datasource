package com.winterframework.pt.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PT_USER_CUSTOMER database table.
 * 
 */
@Entity
@Table(name="PT_USER_CUSTOMER")
@NamedQuery(name="PtUserCustomer.findAll", query="SELECT p FROM PtUserCustomer p")
public class PtUserCustomer implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PtUserCustomerPK id;

	@Column(name="ACTIVE")
	private Integer active;

	@Column(name="AVAIL_BAL")
	private BigDecimal availBal;

	@Column(name="BALANCE")
	private BigDecimal balance;

	@Column(name="FF_ACCOUNT")
	private String ffAccount;

	@Column(name="FF_PARENT_ID")
	private Long ffParentId;

	@Column(name="GAME_LIST")
	private String gameList;

	@Column(name="GMT_LOGIN")
	private Date gmtLogin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GMT_REGISTER")
	private Date gmtRegister;

	@Column(name="GMT_UPDATE")
	private Date gmtUpdate;

	@Column(name="IS_CONFIG_PWD")
	private Integer isConfigPwd;

	@Column(name="IS_WHITE_LIST")
	private Integer isWhiteList;

	@Column(name="PT_PASSWD")
	private String ptPasswd;

	@Column(name="REGISTER_IP")
	private BigDecimal registerIp;

	@Column(name="REGISTER_STATUS")
	private Integer registerStatus;

	@Column(name="STATUS")
	private Integer status;

	@Column(name="SUB_ID")
	private BigDecimal subId;

	@Column(name="VIP_LVL")
	private Integer vipLvl;

	public PtUserCustomer() {
	}

	public PtUserCustomerPK getId() {
		return id;
	}

	public void setId(PtUserCustomerPK id) {
		this.id = id;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public BigDecimal getAvailBal() {
		return availBal;
	}

	public void setAvailBal(BigDecimal availBal) {
		this.availBal = availBal;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getFfAccount() {
		return ffAccount;
	}

	public void setFfAccount(String ffAccount) {
		this.ffAccount = ffAccount;
	}

	public Long getFfParentId() {
		return ffParentId;
	}

	public void setFfParentId(Long ffParentId) {
		this.ffParentId = ffParentId;
	}

	public String getGameList() {
		return gameList;
	}

	public void setGameList(String gameList) {
		this.gameList = gameList;
	}

	public Date getGmtLogin() {
		return gmtLogin;
	}

	public void setGmtLogin(Date gmtLogin) {
		this.gmtLogin = gmtLogin;
	}

	public Date getGmtRegister() {
		return gmtRegister;
	}

	public void setGmtRegister(Date gmtRegister) {
		this.gmtRegister = gmtRegister;
	}

	public Date getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Date gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

	public Integer getIsConfigPwd() {
		return isConfigPwd;
	}

	public void setIsConfigPwd(Integer isConfigPwd) {
		this.isConfigPwd = isConfigPwd;
	}

	public Integer getIsWhiteList() {
		return isWhiteList;
	}

	public void setIsWhiteList(Integer isWhiteList) {
		this.isWhiteList = isWhiteList;
	}

	public String getPtPasswd() {
		return ptPasswd;
	}

	public void setPtPasswd(String ptPasswd) {
		this.ptPasswd = ptPasswd;
	}

	public BigDecimal getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(BigDecimal registerIp) {
		this.registerIp = registerIp;
	}

	public Integer getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(Integer registerStatus) {
		this.registerStatus = registerStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getSubId() {
		return subId;
	}

	public void setSubId(BigDecimal subId) {
		this.subId = subId;
	}

	public Integer getVipLvl() {
		return vipLvl;
	}

	public void setVipLvl(Integer vipLvl) {
		this.vipLvl = vipLvl;
	}
}