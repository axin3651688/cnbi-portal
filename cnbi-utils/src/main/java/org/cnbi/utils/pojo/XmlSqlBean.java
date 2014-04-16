package org.cnbi.utils.pojo;

import java.io.Serializable;
import java.util.Map;

public class XmlSqlBean implements Serializable {

	 /**
	 * 
	 */
	  private static final long serialVersionUID = 1L;

	  private String id = "sql";
	  
	  private boolean debug;
	  
	  private String name;
	  
	  private Map<String,Object>  map;
	  
	  public XmlSqlBean(){}
	  
	  

	public XmlSqlBean( boolean debug, String name,
			Map<String, Object> map) {
		super();
		this.debug = debug;
		this.name = name;
		this.map = map;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	  
	  

}
