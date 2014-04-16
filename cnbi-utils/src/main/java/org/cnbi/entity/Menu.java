package org.cnbi.entity;

import org.cnbi.entity.base.Dim;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月11日下午2:15:38
 */
public class Menu extends Dim {
	
	
	private static final long serialVersionUID = 1L;

	@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
	public String favicon = "favicon";
	
	
	public Menu(String scode, String sname,String spcode) {
		super();
		this.scode = scode;
		this.spcode = spcode;
		this.sname = sname;
	}
	public Menu(){}

	public String getFavicon() {
		return favicon;
	}

	public void setFavicon(String favicon) {
		this.favicon = favicon;
	}
	
	

}
