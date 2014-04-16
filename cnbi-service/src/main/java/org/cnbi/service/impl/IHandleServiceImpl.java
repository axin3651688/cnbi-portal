package org.cnbi.service.impl;

import java.util.List;
import java.util.Map;

import org.cnbi.dao.IHandleDao;
import org.cnbi.entity.User;
import org.cnbi.service.IHandleService;
import org.cnbi.utils.exception.BusinessException;
import org.cnbi.utils.pagination.PagingUtil;
import org.cnbi.utils.pojo.DynamicBean;
import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.pojo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("handleService")
public class IHandleServiceImpl implements IHandleService {
	
	
	@Autowired
	private IHandleDao handleDao;

	@Override
	public List<?> query(String sql, Object object){
		try {
			return handleDao.query(sql, object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<?> query(String sql, Object object, Object paramObj){
		try {
			return handleDao.query(sql, object, paramObj);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<DynamicBean> queryDynamicBeanList(String sql, Object object){
		try {
			return handleDao.queryDynamicBeanList(sql, object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<?> queryDynamicBeanList(String sql, Object object, boolean isDynamicBean){
		try {
			return handleDao.queryDynamicBeanList(sql, object, isDynamicBean);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public List<Map> queryListMapBean(String sql, Object object){
		try {
			return handleDao.queryListMapBean(sql, object);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
	}

	@Override
	public MsgBean execute(String sql, Object object){
		try {
			return handleDao.execute(sql, object);
		} catch (Exception e) {
			throw new BusinessException("执行>>>>>\n"+sql+"时出错了！"+e.getMessage());
		}
		
	}

	@Override
	public Pagination queryPagination(PagingUtil page, Object object){
		try {
			return handleDao.queryPagination(page, object);
		} catch (Exception e) {
			throw new BusinessException("查询分页数据第【"+page.getCurrentPage()+"】页,每一页【"+page.getNumPerPage()+"】条记录时出错了！"+e.getMessage());
		}
		
	}

	@Override
	public int queryCount(String sql){
		try {
			return handleDao.queryCount(sql);
		} catch (Exception e) {
			throw new BusinessException("查询总数时出错了！"+e.getMessage());
		}
		
	}

	@Override
	public boolean queryUserLicenseLastTimeWhetherPastdue(String sql){

		try {
			return handleDao.queryUserLicenseLastTimeWhetherPastdue(sql);
		} catch (Exception e) {
			throw new BusinessException("检查license文件时出错了！"+e.getMessage());
		}
	}

	/* 
	 * @data 2014年4月10日  复写的方法
	 * @see org.cnbi.service.IHandleService#query()
	 */
	@Override
	public Object query() {
		try {
			return query("select", new User());
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
