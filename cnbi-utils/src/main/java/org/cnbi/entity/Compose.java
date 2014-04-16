package org.cnbi.entity;

import java.util.List;



/**
 * Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年3月12日下午6:11:55 事实字典表 DW_Compose
 */
public class Compose extends org.cnbi.entity.base.Dim {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer nid;
	/**
	 * 维度名称
	 */
	private String field;
	/**
	 * 维度表
	 */
	private String factTable;
	/**
	 * 此维度所属的类型 ，度量还是维度
	 */
	private String type;
	/**
	 * 主题外建
	 */
	private String subject;
	/**
	 * 此维度数据
	 */
	private List<?> dimDatas;

	public Compose() {
	}

	public Compose(String subject) {
		this.subject = subject;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFactTable() {
		return factTable;
	}

	public void setFactTable(String factTable) {
		this.factTable = factTable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the dimDatas
	 */
	public List<?> getDimDatas() {
		return dimDatas;
	}

	/**
	 * @param dimDatas the dimDatas to set
	 */
	public void setDimDatas(List<?> dimDatas) {
		this.dimDatas = dimDatas;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
