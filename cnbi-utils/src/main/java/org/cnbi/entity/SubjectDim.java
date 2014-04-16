package org.cnbi.entity;

import org.cnbi.entity.base.Dim;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月12日下午7:21:05
 * 所有维度的统称
 */
public class SubjectDim extends Dim {
	
	
	private static final long serialVersionUID = 1L;


	public SubjectDim(){}
	
	
	public SubjectDim(String scode,String sname,String spcode){
		this.scode = scode;
		this.sname = sname;
		this.spcode = spcode;
	}
	public SubjectDim(String scode,String sname){
		this.scode = scode;
		this.sname = sname;
	}
	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getScode() {
		return scode;
	}


	public void setScode(String scode) {
		this.scode = scode;
	}


	public String getSpcode() {
		return spcode;
	}


	public void setSpcode(String spcode) {
		this.spcode = spcode;
	}

}
