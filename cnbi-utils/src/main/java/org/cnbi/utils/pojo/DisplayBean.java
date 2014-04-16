package org.cnbi.utils.pojo;

import java.io.Serializable;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月27日下午6:45:05
 */
public class DisplayBean implements Serializable {
	
	/**
	 * @Author 龚佳新
	 * @Time 2014年3月27日下午6:46:23
	*/
	private static final long serialVersionUID = 1L;

	private boolean showScode = true;
	
	private boolean showSname = false;
	
	public DisplayBean(){}

	/**
	 * @param showScode
	 * @param showSname
	 * @Author 龚佳新
	 * @Time 2014年3月27日下午6:46:13
	*/
	
	public DisplayBean(boolean showScode, boolean showSname) {
		super();
		this.showScode = showScode;
		this.showSname = showSname;
	}

	/**
	 * @return the showScode
	 */
	public boolean isShowScode() {
		return showScode;
	}

	/**
	 * @param showScode the showScode to set
	 */
	public void setShowScode(boolean showScode) {
		this.showScode = showScode;
	}

	/**
	 * @return the showSname
	 */
	public boolean isShowSname() {
		return showSname;
	}

	/**
	 * @param showSname the showSname to set
	 */
	public void setShowSname(boolean showSname) {
		this.showSname = showSname;
	}
	
	

}
