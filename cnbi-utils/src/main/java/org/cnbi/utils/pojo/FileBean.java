package org.cnbi.utils.pojo;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 * @date 2014年4月11日上午9:09:56
 * @cnbi-utils
 */
public class FileBean implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  int id;
	 private String directory;
	 private String name;
	 private String size;
	 private String createTime;
	 private String modifyTime;
	 
	 private String href;
	 private List<FileBean> list;
	 
	 private String scode;
	 
	 public FileBean(){}
    
	public FileBean(String directory, String name, String size,
			String createTime, String modifyTime,List<FileBean> list) {
		super();
		this.directory = directory;
		this.name = name;
		this.size = size;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.list = list;
	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	};
	
	
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

	@Override
	public String toString() {
		StringBuffer sf = new StringBuffer("{");
		Field[] fields =    this.getClass().getDeclaredFields();
		for (int i= 0;i< fields.length; i++) {
			  Field field = fields[i];  
			String name =field.getName();
			try {
				Object temp = field.get(this);
				if(null!=temp){
					sf.append("\""+name+"\":").append("\""+temp+"\"").append(",");
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		}
		
		String msg = sf.substring(0, sf.length()-1)+"}";
		return  msg;
	}

	public List<FileBean> getList() {
		return list;
	}

	public void setList(List<FileBean> list) {
		this.list = list;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
   
}
