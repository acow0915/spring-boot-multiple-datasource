package com.winterframework.pt.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "PT_GAME_BET_RECORD")
public class PtGameBetRecord {

	/***/
	@Id
	@Column(name = "ID")
	private Long id;
	
	/***/
	@Column(name = "GAME_ID")
	private String gameId;
	
	/***/
	@Column(name = "SN")
	private String sn;
	
	/***/
	@Column(name = "CURRENTBET")
	private BigDecimal currentbet;
	
	/***/
	@Column(name = "PRIZE")
	private BigDecimal prize;
	
	/***/
	@Column(name = "PT_ACCOUNT")
	private String ptAccount;
	
	/***/
	@Column(name = "GAME_NAME")
	private String gameName;
	
	/***/
	@Column(name = "GMT_CREATE")
	private Date gmtCreate;
	
	/***/
	@Column(name = "GMT_WRITE")
	private Date gmtWrite;
	
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "AGENT_TYPE")
	private Integer agentType;
	@Column(name = "WIN")
	private Integer win;
	@Column(name = "BALANCE")
	private BigDecimal balance;
	@Column(name = "GAME_RULE")
	private Long gameRule;
	@Column(name = "ONLINE")
	private Integer online;
	@Column(name = "PROGRESSIVEBET")
	private BigDecimal progressivebet;
	@Column(name = "PROGRESSIVEWIN")
	private BigDecimal progressivewin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public BigDecimal getCurrentbet() {
		return currentbet;
	}

	public void setCurrentbet(BigDecimal currentbet) {
		this.currentbet = currentbet;
	}

	public BigDecimal getPrize() {
		return prize;
	}

	public void setPrize(BigDecimal prize) {
		this.prize = prize;
	}

	public String getPtAccount() {
		return ptAccount;
	}

	public void setPtAccount(String ptAccount) {
		this.ptAccount = ptAccount;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAgentType() {
		return agentType;
	}

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Long getGameRule() {
		return gameRule;
	}

	public void setGameRule(Long gameRule) {
		this.gameRule = gameRule;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public BigDecimal getProgressivebet() {
		return progressivebet;
	}

	public void setProgressivebet(BigDecimal progressivebet) {
		this.progressivebet = progressivebet;
	}

	public BigDecimal getProgressivewin() {
		return progressivewin;
	}

	public void setProgressivewin(BigDecimal progressivewin) {
		this.progressivewin = progressivewin;
	}
}
