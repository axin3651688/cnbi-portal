package org.cnbi.utils.pojo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnore;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;
public class DynamicBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


   private  Object object = null;  
   
   public Map<?,?> getMap() {
		Map map = new HashMap();
		Set keySet = beanMap.keySet();
		Iterator<?> it = keySet.iterator();
		while(it.hasNext()){
			Object key = it.next();
			map.put(key, beanMap.get(key));
		}
   	return map;
		
	}
 
   public BeanMap getBeanMap() {
		return beanMap;
	}

	public void setBeanMap(BeanMap beanMap) {
		this.beanMap = beanMap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	@JsonIgnore
   private  BeanMap beanMap = null;  
 
   public DynamicBean() {  
       super();  
   }  
     
   public DynamicBean(Map<?, ?> propertyMap) {  
     this.object = generateBean(propertyMap);  
     this.beanMap = BeanMap.create(this.object);  
   }  
    public void setValue(String property, Object value) {  
     beanMap.put(property, value);  
   }  
 
  
   public Object getValue(String property) {  
     return beanMap.get(property);  
   }  
 
   public Object getObject() {  
     return this.object;  
   }  
 
   /** 
    * @param propertyMap 
    * @return 
    */  
   private Object generateBean(Map<?, ?> propertyMap) {  
     BeanGenerator generator = new BeanGenerator();  
     Set<?> keySet = propertyMap.keySet();  
     for (Iterator<?> i = keySet.iterator(); i.hasNext();) {  
      String key = (String) i.next();  
      generator.addProperty(key, (Class<?>) propertyMap.get(key));  
     }  
     return   generator.create();
   }  
 
}  

