package org.cnbi.utils.pojo;
/**
 * 
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月14日上午8:58:08
 */
public class MsgBean {
	  public boolean flag =true;
	  public String text;
	  public MsgBean(){}
	  public MsgBean(String text){
		  this.text = text;
	  }
	  public MsgBean(boolean flag,String text){
		  this.text = text;
		  this.flag = flag;
	  }

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	  
	  

}
