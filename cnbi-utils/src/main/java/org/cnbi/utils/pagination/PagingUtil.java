package org.cnbi.utils.pagination;

public class PagingUtil {
	
	  // 一页显示的记录数
		private int numPerPage = 10;
		
		// 当前页码
		private int currentPage;
		
		private String sql;

		/**
		 * @return the numPerPage
		 */
		public int getNumPerPage() {
			return numPerPage;
		}

		/**
		 * @param numPerPage the numPerPage to set
		 */
		public void setNumPerPage(int numPerPage) {
			this.numPerPage = numPerPage;
		}

		/**
		 * @return the currentPage
		 */
		public int getCurrentPage() {
			return currentPage;
		}

		/**
		 * @param currentPage the currentPage to set
		 */
		public void setCurrentPage(int currentPage) {
			this.currentPage = currentPage;
		}

		/**
		 * @return the sql
		 */
		public String getSql() {
			return sql;
		}

		/**
		 * @param sql the sql to set
		 */
		public void setSql(String sql) {
			this.sql = sql;
		}
		
		

}
