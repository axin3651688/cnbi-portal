package org.cnbi.utils.pojo;
import java.util.List;

import org.cnbi.utils.pagination.PagingUtil;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
/**
 * 
 * @author 龚佳新
 * @date 2014-04-10
 *  分页工具类
 */
public class Pagination/* extends JdbcDaoSupport */{
	public static final int NUMBERS_PER_PAGE = 10;
	// 一页显示的记录数
	private int numPerPage;
	// 记录总数
	private int totalRows;
	// 总页数
	private int totalPages;
	// 当前页码
	private int currentPage;
	// 起始行数
	private int startIndex;
	// 结束行数
	private int lastIndex;
	// 结果集存放List
	private List<?> resultList;
	/**
	 * 每页显示10条记录的构造函数,使用该函数必须先给Pagination设置currentPage，jTemplate初值
	 * 
	 * @param sql
	 *            oracle语句
	 *//*
	public Pagination(String sql,NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		new Pagination(sql, currentPage, NUMBERS_PER_PAGE, namedParameterJdbcTemplate);
	}*/

	/**
	 * 分页构造函数
	 * 
	 * @param sql
	 *            根据传入的sql语句得到一些基本分页信息
	 * @param currentPage
	 *            当前页
	 * @param numPerPage
	 *            每页记录数
	 * @param jTemplate
	 *            JdbcTemplate实例
	 * @throws Exception 
	 */
	public Pagination(PagingUtil page,Object object){
		numPerPage = page.getNumPerPage();
		currentPage = page.getCurrentPage();
		String sql = page.getSql();
		// 设置每页显示记录数
		setNumPerPage(numPerPage);
		// 设置要显示的页数
		setCurrentPage(currentPage);
		/*// 计算总记录数
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");*/
		// 总记录数
	//	setTotalRows(baseDao.queryCount(sql));
		// 计算总页数
		setTotalPages();
		// 计算起始行数
		setStartIndex();
		// 计算结束行数
		setLastIndex();
		System.out.println("lastIndex=" + lastIndex);// ////////////////
		// 构造oracle数据库的分页语句
//		String listSql = getMySQLPageSQL(sql, startIndex, numPerPage);
//		List<?> list = baseDao.query(listSql, object);
//		// 装入结果集
//		setResultList(list);
	}

	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	// 计算总页数
	public void setTotalPages() {
		if (totalRows % numPerPage == 0) {
			this.totalPages = totalRows / numPerPage;
		} else {
			this.totalPages = (totalRows / numPerPage) + 1;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (currentPage - 1) * numPerPage;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	// 计算结束时候的索引
	public void setLastIndex() {
		System.out.println("totalRows=" + totalRows);// /////////
		System.out.println("numPerPage=" + numPerPage);// /////////
		if (totalRows < numPerPage) {
			this.lastIndex = totalRows;
		} else if ((totalRows % numPerPage == 0) || (totalRows % numPerPage != 0 && currentPage < totalPages)) {
			this.lastIndex = currentPage * numPerPage;
		} else if (totalRows % numPerPage != 0 && currentPage == totalPages) {// 最后一页
			this.lastIndex = totalRows;
		}
	}

}
