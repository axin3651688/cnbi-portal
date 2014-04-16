package org.cnbi.utils.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;


public class RecordBean implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
	public String id="";
    
	public String name;
	  @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
	public String exp;
	
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
    public String formula;
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
    public String attributes;
    
    @JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
    public String params[];
    
    public RecordBean(String id){
    	this.id = id;
    }
    
    public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public RecordBean(){}
    
    public RecordBean(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
    public RecordBean(String id, String name,String formula) {
  		super();
  		this.id = id;
  		this.name = name;
  		this.formula = formula;
  	}
	
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
}
