package org.cnbi.utils.convert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OjbectConvertUtil {
	/**
	 * SET转换MAP
	 * @param str
	 * @return
	 */
	public static Map<Object, Object> SetToMap(Set<Object> setobj) {
		Map<Object, Object> map = getHashMap();
		for (Object obj: setobj) {
			  System.out.println(obj.toString());
		}
		for (Iterator iterator = setobj.iterator(); iterator.hasNext();) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
			map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
		}
		/*for (Map.Entry<Object, Object> entry:map.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
		}*/
		return map;

	}
	
	/**
	 * 获取Map对象
	 */
	public static Map<Object, Object> getHashMap() {
		return new HashMap<Object, Object>();
	}
}
