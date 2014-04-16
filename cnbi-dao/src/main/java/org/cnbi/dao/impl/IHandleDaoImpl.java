package org.cnbi.dao.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cnbi.dao.IHandleDao;
import org.cnbi.entity.SysLog;
import org.cnbi.utils.pagination.PagingUtil;
import org.cnbi.utils.pojo.DynamicBean;
import org.cnbi.utils.pojo.Pagination;
import org.springframework.stereotype.Repository;
@Repository
public class IHandleDaoImpl extends org.cnbi.dao.base.BaseDao implements IHandleDao {
	@Override
	public Pagination queryPagination(PagingUtil page, Object object)  {
		Pagination paging = new Pagination(page, object) ;
		String listSql = getMySQLPageSQL(page.getSql(), paging.getStartIndex(), paging.getNumPerPage());
		List<?> list = super.query(listSql, object);
		// 计算总记录数
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSQL.append(page.getSql());
		totalSQL.append(" ) totalTable ");
		paging.setTotalRows(super.queryCount(totalSQL.toString()));
		// 装入结果集
		paging.setResultList(list);

		return paging;
	}


	@Override
	public boolean queryUserLicenseLastTimeWhetherPastdue(String sql)  {
		List<SysLog> list = (List<SysLog>) super.query(sql, new SysLog());
		for(SysLog log:list){
			long lastLoginTime = log.getLoginTime().getTime();
			long now = new Date().getTime();
			if (lastLoginTime > now){
				return true;
			}
		}
		return false;
	}

	

	@Override
	public List<Map> queryListMapBean(String sql, Object object)  {
		List<DynamicBean> list = super.queryDynamicBeanList(sql, object);
		List<Map> resultList = new ArrayList<Map>();
		for(DynamicBean bean:list){
			resultList.add(bean.getMap());
		}
		return resultList;
	}

}
