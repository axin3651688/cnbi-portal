package org.cnbi.utils.dynamic;
import java.util.HashMap;

import org.cnbi.utils.pojo.DynamicBean;
public class DynamicBeanUtil {
	private static final String DEFAULT_TYPE = "java.lang.String";
	
	/**
	 * 获取动态bean
	 * @param temp
	 * @param paramsType
	 * @return
	 */
	public static DynamicBean getDynamicBean(String[] properties, String[] typeArr) {
		int proLen = properties.length;
		if(null != typeArr){
			int typeLen =  typeArr.length;
			if(proLen != typeLen){
				throw new RuntimeException("获取动态对象时传入了错误的长度！属性长度为==【"+proLen+"】属性类型长度为==【"+typeLen+"】");

			}
		}
	    HashMap<Object, Class<?>> propertyMap = new HashMap<Object, Class<?>>();
		for (int i = 0; i < proLen; i++) {
			try {
				Class<?>  clazz= null;
				if(null == typeArr){
					  clazz=  Class.forName(DEFAULT_TYPE);
				}else{
					clazz=  Class.forName(typeArr[i]);
				}
				propertyMap.put(properties[i],clazz);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("获取【" + properties[i] + "】字段类型出错！");
			}
		}
		return new DynamicBean(propertyMap);
	}

}
