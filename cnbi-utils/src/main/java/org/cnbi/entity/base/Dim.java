package org.cnbi.entity.base;
import java.io.Serializable;

import org.cnbi.utils.pojo.CommonBean;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年3月10日下午6:57:41
 */
public class Dim extends CommonBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@JsonProperty("leaf")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
	public int isleaf;

	@JsonIgnore
	public int isactive;

	public String state = "closed";

	public Dim() {
	}

	public int getIsleaf() {
		return isleaf;
	}

	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getState() {
		if (this.getIsleaf() == 0) {
		} else {
			return "open";
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
	public Dim(String scode, String sname) {
		super();
		this.scode = scode;
		this.sname = sname;
	}

}
