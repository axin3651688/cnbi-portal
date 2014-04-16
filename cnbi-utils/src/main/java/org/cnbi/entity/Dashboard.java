package org.cnbi.entity;

import java.util.List;

import org.cnbi.entity.base.Dim;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月12日上午10:56:41
 */
public class Dashboard extends Dim {
	
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private String query;
	
	@JsonIgnore
	private String subject;
	
	public Dashboard(){}
	
	public Dashboard(String scode, String sname, String spcode,int isleaf, int isactive) {
		super();
		this.scode = scode;
		this.sname = sname;
		this.spcode = spcode;
		this.isleaf = isleaf;
		this.isactive = isactive;
	}
	 /**
     *  @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
     *  表示本类做为json输出的时候 如果此children为null了，则不做为json的属性输出
     */
	@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
	private List<Dashboard> children;
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<Dashboard> getChildren() {
		return children;
	}
	public void setChildren(List<Dashboard> children) {
		this.children = children;
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
		if(this.getIsleaf() == 0){
    	}else{
    		return "open";
    	}
		return state;
	}


	public void setState(String state) {
		this.state = state;
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

	/**
	 * @return the subject
	 */
	public String getSubject() {
		if(null !=subject)this.subject = subject.trim();
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
