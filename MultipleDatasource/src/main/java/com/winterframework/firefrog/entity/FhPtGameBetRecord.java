package com.winterframework.firefrog.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the PT_GAME_BET_RECORD database table.
 * 
 */
@Entity
@Table(name="FH_PT_GAME_BET_RECORD")
public class FhPtGameBetRecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	
	@Column(name="ACCOUNT")
	private String account;

	@Column(name="AGENT_TYPE")
	private BigDecimal agentType;

	@Column(name="BALANCE")
	private BigDecimal balance;

	@Column(name="CURRENTBET")
	private BigDecimal currentbet;

	@Column(name="GAME_ID")
	private String gameId;

	@Column(name="GAME_NAME")
	private String gameName;

	@Column(name="GAME_RULE")
	private BigDecimal gameRule;

	@Column(name="GMT_CREATE")
	private Date gmtCreate;

	@Column(name="GMT_WRITE")
	private Date gmtWrite;

	@Column(name="PRIZE")
	private BigDecimal prize;

	@Column(name="PROGRESSIVEBET")
	private BigDecimal progressivebet;

	@Column(name="PROGRESSIVEWIN")
	private BigDecimal progressivewin;

	@Column(name="PT_ACCOUNT")
	private String ptAccount;

	@Column(name="PT_ONLINE")
	private BigDecimal online;

	@Column(name="SN")
	private String sn;

	@Column(name="STATUS")
	private BigDecimal status;
	
	@Column(name="USER_ID")
	private Long userId;

	@Column(name="WIN")
	private BigDecimal win;

	public FhPtGameBetRecord() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getAgentType() {
		return this.agentType;
	}

	public void setAgentType(BigDecimal agentType) {
		this.agentType = agentType;
	}

	public BigDecimal getBalance() {
		return this.balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getCurrentbet() {
		return this.currentbet;
	}

	public void setCurrentbet(BigDecimal currentbet) {
		this.currentbet = currentbet;
	}

	public String getGameId() {
		return this.gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public BigDecimal getGameRule() {
		return this.gameRule;
	}

	public void setGameRule(BigDecimal gameRule) {
		this.gameRule = gameRule;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtWrite() {
		return gmtWrite;
	}

	public void setGmtWrite(Date gmtWrite) {
		this.gmtWrite = gmtWrite;
	}

	public BigDecimal getPrize() {
		return this.prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public BigDecimal getProgressivebet() {
		return this.progressivebet;
	}

	public void setProgressivebet(BigDecimal progressivebet) {
		this.progressivebet = progressivebet;
	}

	public BigDecimal getProgressivewin() {
		return this.progressivewin;
	}

	public void setProgressivewin(BigDecimal progressivewin) {
		this.progressivewin = progressivewin;
	}

	public String getPtAccount() {
		return this.ptAccount;
	}

	public void setPtAccount(String ptAccount) {
		this.ptAccount = ptAccount;
	}

	public BigDecimal getOnline() {
		return online;
	}

	public void setOnline(BigDecimal online) {
		this.online = online;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public BigDecimal getWin() {
		return this.win;
	}

	public void setWin(BigDecimal win) {
		this.win = win;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}