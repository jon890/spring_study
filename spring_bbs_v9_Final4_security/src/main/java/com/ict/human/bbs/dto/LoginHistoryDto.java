package com.ict.human.bbs.dto;

public class LoginHistoryDto {
	private int idx;
	private String id;
	private int retry;
	private long lastFailedLogin;
	private long lastSuccessedLogin;
	private String clientIp;
	private String sessionId;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRetry() {
		return retry;
	}
	public void setRetry(int retry) {
		this.retry = retry;
	}
	public long getLastFailedLogin() {
		return lastFailedLogin;
	}
	public void setLastFailedLogin(long lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}
	public long getLastSuccessedLogin() {
		return lastSuccessedLogin;
	}
	public void setLastSuccessedLogin(long lastSuccessedLogin) {
		this.lastSuccessedLogin = lastSuccessedLogin;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	@Override
	public String toString() {
		return "LoginHistoryDto [idx=" + idx + ", id=" + id + ", retry=" + retry + ", lastFailedLogin="
				+ lastFailedLogin + ", lastSuccessedLogin=" + lastSuccessedLogin + ", clientIp=" + clientIp
				+ ", sessionId=" + sessionId + "]";
	}

}