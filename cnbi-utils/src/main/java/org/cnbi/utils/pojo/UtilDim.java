package org.cnbi.utils.pojo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

public class UtilDim extends CommonBean {
	
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Integer sort;
	
	@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
	public String field;

	/**
	 * @return the sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public UtilDim(){}
	public UtilDim(String scode, String sname) {
			this.scode = scode;
			this.sname = sname;
	}
	public UtilDim(String scode, String sname,String field) {
		this.scode = scode;
		this.sname = sname;
		this.field = field;
}
	
	public UtilDim(String scode, String spcode, String sname, String field) {
		super();
		this.scode = scode;
		this.sname = sname;
		this.spcode = spcode;
		this.field = field;
	}
	

}
