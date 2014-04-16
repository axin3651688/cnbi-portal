package org.cnbi.utils.pojo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月20日上午10:43:51
 */
public abstract class CommonBean implements Serializable{
	/**
	 * @Author 龚佳新
	 * @Time 2014年3月23日上午11:30:09
	*/
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	public String scode;

	@JsonProperty("text")
	public String sname;

	@JsonProperty("nid")
	public String spcode;
	
	public CommonBean(){}
	
	

	/**
	 * @param scode
	 * @param sname
	 * @param spcode
	 * @Author 龚佳新
	 * @Time 2014年3月20日上午10:46:37
	*/
	
	public CommonBean(String scode, String sname, String spcode) {
		super();
		this.scode = scode;
		this.sname = sname;
		this.spcode = spcode;
	}



	/**
	 * @return the scode
	 */
	public String getScode() {
		return scode;
	}

	/**
	 * @param scode the scode to set
	 */
	public void setScode(String scode) {
		this.scode = scode;
	}

	/**
	 * @return the sname
	 */
	public String getSname() {
		return sname;
	}

	/**
	 * @param sname the sname to set
	 */
	public void setSname(String sname) {
		this.sname = sname;
	}

	/**
	 * @return the spcode
	 */
	public String getSpcode() {
		return spcode;
	}

	/**
	 * @param spcode the spcode to set
	 */
	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}
	
}
