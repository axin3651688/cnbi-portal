package org.cnbi.utils.properties;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.utils.convert.OjbectConvertUtil;
/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年3月25日上午9:16:13
 */
public class PropertyUtil {
	
	private static final Log logger = LogFactory.getLog(PropertyUtil.class);
	
	//private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("properties/companyInfo");
	
	/**
	 * 获取Properties<==>就是一个map
	 * @param propertyPath
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月25日上午9:32:49
	 */
	public static Properties getProperties(String propertyPath){
		Properties  propertyMap = new Properties();
		propertyPath =  "/"+propertyPath+".properties";
		InputStream in = PropertyUtil.class.getResourceAsStream(propertyPath);
		try {
			propertyMap.load(in);
			//Set<?> keyValue = propertyMap.keySet();
			//for (Iterator<?> it = keyValue.iterator(); it.hasNext();) {
			//	String key = (String) it.next();
				//logger.info("key=="+key+"--value=="+propertyMap.get(key));
			//}
			return propertyMap;
		} catch (IOException e) {
			logger.error("加载【"+propertyPath+"】文件出错了！");
			throw new RuntimeException("加载【"+propertyPath+"】文件出错了！\n"+e.getMessage());
		}
	}
	/**
	 * 获取配置文件参数
	 * @param name
	 * @return
	 */
	/*public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}*/
	/**
	 * 获取配置文件参数
	 * @param name
	 * @return
	 */
	public static final Map<Object, Object> getConfigMap(String path) {
		ResourceBundle bundle = ResourceBundle.getBundle(path);
		Set set = bundle.keySet();
		return OjbectConvertUtil.SetToMap(set);
	}
	

}
