package org.cnbi.entity;

import java.util.Date;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年4月8日下午1:30:48
 */
public class SysLog {
	
	private String type;
	
	private Date loginTime;
	
	private String hostName;
	
	private int logId;
	
	private String suser;
	
	private String broswer;//用户浏览器类型
	
	public SysLog(){}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * @return the logId
	 */
	public int getLogId() {
		return logId;
	}

	/**
	 * @param logId the logId to set
	 */
	public void setLogId(int logId) {
		this.logId = logId;
	}

	/**
	 * @return the suser
	 */
	public String getSuser() {
		return suser;
	}

	/**
	 * @param suser the suser to set
	 */
	public void setSuser(String suser) {
		this.suser = suser;
	}

	/**
	 * @return the broswer
	 */
	public String getBroswer() {
		return broswer;
	}

	/**
	 * @param broswer the broswer to set
	 */
	public void setBroswer(String broswer) {
		this.broswer = broswer;
	}
	
	

}
