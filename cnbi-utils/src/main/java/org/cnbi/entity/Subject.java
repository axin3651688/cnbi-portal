package org.cnbi.entity;

import org.cnbi.entity.base.Dim;
import org.cnbi.utils.tools.Constants;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年3月12日下午6:05:51 主题表 DW_Subject
 */
public class Subject extends Dim {

	private static final long serialVersionUID = 1L;

	private String split = Constants.UNDERLINE;

	private String dim = Constants.DIM;

	private String fact = Constants.FACT;

	private String factTable;

	public Subject() {
	}

	public Subject(String scode, String sname, String spcode) {
		this.scode = scode;
		this.sname = sname;
		this.spcode = spcode;
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

	public String getSplit() {
		return split;
	}

	public void setSplit(String split) {
		this.split = split;
	}

	public String getDim() {
		return dim;
	}

	public void setDim(String dim) {
		this.dim = dim;
	}

	public String getFact() {
		return fact;
	}

	public void setFact(String fact) {
		this.fact = fact;
	}

	public String getFactTable() {
		return factTable;
	}

	public void setFactTable(String factTable) {
		this.factTable = factTable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
