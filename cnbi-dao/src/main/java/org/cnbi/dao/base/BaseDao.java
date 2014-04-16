package org.cnbi.dao.base;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.utils.dynamic.DynamicBeanUtil;
import org.cnbi.utils.pojo.DynamicBean;
import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.string.StringUtil;
import org.cnbi.utils.tools.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
/**
 * 
 *  凡是dao层的方法,大家一定要严格遵守这种定义：
 *       add 添加方法的开头 
 *       update 更新方法的开头 
 *       del 删除方法的开头 
 *       query 查询方法的开头
 * 2014中国经邦. All rights reserved.   Copyright ©
 * @Author 龚佳新
 * @Time 2014年3月19日上午9:02:08
 */
public class BaseDao {
	protected static  final Log logger = LogFactory.getLog(BaseDao.class);
	
	
	@Autowired
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//@Autowired
	//protected org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;
	@Autowired
	protected DataSource dataSource;
		
	public BaseDao(){
		 
	}


	/**
	 * 
	 * @param sql
	 *            语句
	 * @param paramMap
	 *            参数map
	 * @param obj
	 *            List里放的对象类型
	 * @return list
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private List<?> query(String sql, Map paramMap, Object obj)  {
		return namedParameterJdbcTemplate.query(sql, paramMap, new BeanPropertyRowMapper(obj.getClass()));
	}
	
	public List<?> query(String sql, Object object, Object paramObj)  {
		if(paramObj instanceof Map){
			return  query(sql, paramObj,object);
		}else{
			if (null != object) {
				Map paramMap = getParameterMap(sql, object);
				sql = sqlParamsReplace(sql, paramMap);
			}
			return query(sql, object);
		}
	}

	/**
	 * 可以是更新，删除与添加
	 * 
	 * @param sql
	 * @param obj
	 */
	public MsgBean execute(String sql, Object obj)  {
		MsgBean msg = new MsgBean(true,"操作成功！");
		if (null != obj) {
			if (obj instanceof Map) {
				namedParameterJdbcTemplate.update(sql, (Map) obj);
			} else {
				BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(obj);
				namedParameterJdbcTemplate.update(sql, paramSource);
			}
		} else {
			namedParameterJdbcTemplate.getJdbcOperations().update(sql);
		}
		return msg;
	

	}

	/**
	 * 查询记录数
	 * 
	 * @param sql
	 * @return
	 */
	public int queryCount(String sql)  {
		int m = namedParameterJdbcTemplate.getJdbcOperations().queryForInt(sql);
		return m;
	}

	protected Map getParameterMap(String sql, Object obj){
		sql = sql + " ";
		if (obj instanceof Map) {
			return (Map) obj;
		}
		Map paramMap = new HashMap();
		Method method = null;
		List<String> paramName = null;
		Class<Object> clazz = (Class<Object>) obj.getClass();
		paramName = getParamName(sql);
		try {
			for (String s : paramName) {
				String methodName = "get" + StringUtil.toUpperCaseFirstOne(s);
				method = clazz.getDeclaredMethod(methodName);
				paramMap.put(s, method.invoke(obj));
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return paramMap;
	}
	/**
	 * 获取SQL参数List
	 * @param sql
	 * @return
	 * @Author 龚佳新
	 * @Time 2014年3月14日上午8:51:09
	 */
	public static List<String> getParamName(String sql){
		List<String> list = new LinkedList<String>();
		Pattern pt = Pattern.compile(":", Pattern.CASE_INSENSITIVE);
		Matcher mc = pt.matcher(sql);
		mc.find();
		mc.reset();
		int i = 0;
		while (mc.find()) {
			int index = sql.indexOf(":", i);
			String item = sql.substring(index + 1, sql.indexOf(" ", index));

			list.add(item.replace(")", ""));
			i = index + 1;

		}
		return list;
	}

	/**
	 * 功能：sql参数替换
	 * 由于暂时没有发现springMVC参数替换在哪个方法里，故自己写的参数替换方法,注意：如果参数值长度在在于1且为string类型：则前后‘不要：如
	 * ：dgd','454','op
	 * 
	 * @zhh 2014/2/17 将方法修改为 静态方法
	 * @param sql
	 * @param paramMap
	 * @return
	 */
	
	
	protected static String sqlParamsReplace(String sql, Map paramMap) {
		String dimspit = Constants.DIM+Constants.UNDERLINE;
		Set set = paramMap.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Object key = it.next();
			Object value = paramMap.get(key);

			if (value instanceof String) {
				String v = (String) value;
				if (key.toString().toLowerCase().contains("table")) {

				}else if(value.toString().contains("=")) {
				} else if(v.contains(Constants.COMMON)) {
					String[] varr = v.split("\\" + Constants.COMMON);
					StringBuffer vf = new StringBuffer("");
					for (int i = 0, len = varr.length; i < len; i++) {
						vf.append("'").append(varr[i]).append("'");
						if (i != len - 1) {
							vf.append(",");
						}
						value = vf.toString();
					}
				} else {
					if(!value.equals("")){
						value = "'" + (String) value + "'";
					}else{//and :helpdim
						// int keyIndex = sql.indexOf(key.toString());
						 //sql = sql.substring(0,keyIndex-5)+sql.substring(keyIndex+8,sql.length());
						sql = sql.replace("and :helpdim", "");
					}
				}
			} else if (key instanceof Integer) {
				value = (String) value;
			} else if (value instanceof Date) {
				value = "'" + value + "'";
			} else if (value instanceof Character) {
				value = "'" + value + "'";
			}
		
			sql = sql.replace(":" + key.toString(), value.toString());
		}
		return sql;
	}
	/**
	 * 此方法也是灵活的方法 可实现查询动态对象的集合
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @
	 * @Author 龚佳新
	 * @Time 2014年3月12日下午2:56:11
	 */
	public List<?> queryDynamicBeanList(String sql, Object object,boolean isDynamicBean) {
		List<DynamicBean> list = queryDynamicBeanList(sql, object);
		if(isDynamicBean == false){
			List<Object> resultList = new ArrayList<Object>();
			for(DynamicBean bean :list){
				resultList.add(bean.getObject());
			}
			return resultList;
		} 
		return list;
		
	}
	/**
	 * 动态List
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 * @
	 * @Author 龚佳新
	 * @Time 2014年3月14日上午9:00:58
	 */
	public List<DynamicBean> queryDynamicBeanList(String sql, Object object)  {
		if (null != object) {
			Map paramMap = getParameterMap(sql, object);
			sql = sqlParamsReplace(sql, paramMap);
		}
		return namedParameterJdbcTemplate.getJdbcOperations().query(sql, new ResultSetExtractor<List<DynamicBean>>() {
			public List<DynamicBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ResultSetMetaData meta = rs.getMetaData();
				int columns = meta.getColumnCount();
				String[] label = new String[columns];
				for (int i = 0; i < label.length; i++) {
					label[i] = meta.getColumnName(i + 1);
				}
				List<DynamicBean> list = new ArrayList<DynamicBean>();
				while (rs.next()) {
					DynamicBean bean = DynamicBeanUtil.getDynamicBean(label,null);
					for (int i = 0; i < columns; i++) {
						Object obj = rs.getObject(i + 1);
						if (obj instanceof Long) {
							obj = String.valueOf(obj);
						} else if (obj instanceof Integer) {
							obj = String.valueOf(obj);
						} else if (obj instanceof BigDecimal) {
							BigDecimal bd = new BigDecimal(obj + "");
							obj = bd.doubleValue();
							//obj = bd.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
						} else if (obj instanceof Date) {
							obj = (Date) obj;
							obj = obj.toString();
						} else if (obj instanceof java.sql.Timestamp) {
							obj = (Timestamp) obj;
							obj = obj.toString();
						}

						if (obj instanceof Double) {
							DecimalFormat format = new DecimalFormat("00.########E0");
							obj = format.format((Double) obj);
						}
						if (null == obj) {
							obj = "0.00";
						}
						bean.setValue(label[i], obj);

					}
					// if(isDynamicBean==true)
					list.add(bean);
					// else
					// list.add(bean.getObject());
				}
				return list;
			}

		});
	}

	public List<?> query(String sql, Object object)  {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
		return namedParameterJdbcTemplate.query(sql, sqlParameterSource, new BeanPropertyRowMapper(object.getClass()));
	}

	public Object queryForObject(String sql, Object object)  {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(object);
		return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, object.getClass());
	}
	
	/**
	 * 构造MySQL数据分页SQL
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public String getMySQLPageSQL(String queryString, Integer startIndex, Integer pageSize) {
		String result = "";
		if (null != startIndex && null != pageSize) {
			result = queryString + " limit " + startIndex + "," + pageSize;
		} else if (null != startIndex && null == pageSize) {
			result = queryString + " limit " + startIndex;
		} else {
			result = queryString;
		}
		return result;
	}

	/**
	 * 构造Sqlserver2005数据分页SQL
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public String getSqlserver2005PageSQL(String queryString, Integer startIndex, Integer pageSize) {
		String result = "";
		
		return result;
	}
	

	/**
	 * 构造Oracle数据分页SQL
	 * @param queryString
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	public String getOraclePageSQL(String queryString, Integer startIndex, Integer pageSize) {
		String result = "";
		/**
		 * StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		 * paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		 * paginationSQL.append(sql);
		 * paginationSQL.append(" ) temp where ROWNUM <= " + lastIndex);
		 * paginationSQL.append(" ) WHERE num > " + startIndex);
		 */
		return result;
	}

}