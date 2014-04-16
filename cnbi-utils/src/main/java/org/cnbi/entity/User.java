package org.cnbi.entity;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月10日下午6:57:20
 */

import java.io.Serializable;

/**
 * User 实体类
 * 
 * @author zhh
 * @version 1.0
 * **/
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String suser;
	private String spassword;
	private String semail;
	private String tcreatetime;
	private String cisadmin;
	private String csex;
	private String strueName;
	private String sphone;
	private String cisenabled;
	private String cauthorize;
	private String _default_;
	private String dim_company;

	public User() {
	}

	public User(String suser, String cisadmin) {
		this.suser = suser;
		this.cisadmin = cisadmin;
	}

	public User(String suser, String spassword, String semail, String tcreatetime, String cisadmin, String csex, String strueName, String sphone, String cisenabled, String cauthorize,
			String _default_, String dim_company) {
		super();
		this.suser = suser;
		this.spassword = spassword;
		this.semail = semail;
		this.tcreatetime = tcreatetime;
		this.cisadmin = cisadmin;
		this.csex = csex;
		this.strueName = strueName;
		this.sphone = sphone;
		this.cisenabled = cisenabled;
		this.cauthorize = cauthorize;
		this._default_ = _default_;
		this.dim_company = dim_company;
	}

	public String getSuser() {
		return suser;
	}

	public void setSuser(String suser) {
		this.suser = suser;
	}

	public String getSpassword() {
		return spassword;
	}

	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getTcreatetime() {
		return tcreatetime;
	}

	public void setTcreatetime(String tcreatetime) {
		this.tcreatetime = tcreatetime;
	}

	public String getCisadmin() {
		return cisadmin;
	}

	public void setCisadmin(String cisadmin) {
		this.cisadmin = cisadmin;
	}

	public String getCsex() {
		return csex;
	}

	public void setCsex(String csex) {
		/*
		 * if(csex.trim()=="on"){ csex = "男"; }else{ csex = "女"; }
		 */
		this.csex = csex;
	}

	public String getStrueName() {
		return strueName;
	}

	public void setStrueName(String strueName) {
		this.strueName = strueName;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getCisenabled() {
		return cisenabled;
	}

	public void setCisenabled(String cisenabled) {
		this.cisenabled = cisenabled;
	}

	public String getCauthorize() {
		return cauthorize;
	}

	public void setCauthorize(String cauthorize) {
		this.cauthorize = cauthorize;
	}

	public String get_default_() {
		return _default_;
	}

	public void set_default_(String _default_) {
		this._default_ = _default_;
	}

	public String getDim_company() {
		return dim_company;
	}

	public void setDim_company(String dim_company) {
		this.dim_company = dim_company;
	}

}
