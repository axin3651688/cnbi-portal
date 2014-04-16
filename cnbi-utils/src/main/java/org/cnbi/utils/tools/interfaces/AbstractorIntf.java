package org.cnbi.utils.tools.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.cnbi.utils.pagination.PagingUtil;
import org.cnbi.utils.pojo.DynamicBean;
import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.pojo.Pagination;

public abstract interface AbstractorIntf {
	/**
	 * 此方法为最为灵活的方法 可实现查询所有实体对象
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @
	 * @Author 龚佳新
	 * @Time 2014年3月12日下午2:55:05
	 */
	public List<?> query(String sql, Object object) ;

	/**
	 * 此方法为最为灵活的方法 可实现查询所有实体对象
	 * 
	 * @param sql
	 * @param object
	 * @param paramObj
	 * @return
	 * @
	 * @Author 龚佳新
	 * @Time 2014年3月12日下午2:55:05
	 */
	public List<?> query(String sql, Object object, Object paramObj) ;

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
	public List<DynamicBean> queryDynamicBeanList(String sql, Object object) ;

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
	public List<?> queryDynamicBeanList(String sql, Object object, boolean isDynamicBean) ;

	/**
	 * 此方法也是灵活的方法 可实现查询 map的集合 因为序列化的问题 才有了此方法
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @
	 * @Author 龚佳新
	 * @Time 2014年3月12日下午2:56:11
	 */
	public List<Map> queryListMapBean(String sql, Object object) ;

	/**
	 * 可以是更新，删除与添加
	 * 
	 * @param sql
	 * @param obj
	 * @
	 */
	public MsgBean execute(String sql, Object object) ;

	/**
	 * 分页查询
	 * 
	 * @param currentPage  当前页
	 * @param numPerPage  每页记录数
	 * @param object     集合里放的对象
	 * @
	 * @return
	 */
	public Pagination queryPagination(PagingUtil page, Object object);
	/**
	 * 查询记录数
	 * @param sql
	 * @return
	 * @
	 */
	public int queryCount(String sql) ;
	
	/**检查license文件是否已经过期,返回true  说明已经过期了
	 * @param sql
	 * @return
	 * @
	 */
	public  boolean queryUserLicenseLastTimeWhetherPastdue(String sql);
}
