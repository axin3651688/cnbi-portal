package org.cnbi.web.system.listener;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cnbi.entity.Compose;
import org.cnbi.entity.Menu;
import org.cnbi.entity.Subject;
import org.cnbi.service.IHandleService;
import org.cnbi.utils.license.LicenseUtil;
import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.pojo.SubjectSqlBean;
import org.cnbi.utils.pojo.UtilDim;
import org.cnbi.utils.pojo.XmlSqlBean;
import org.cnbi.utils.string.StringUtil;
import org.cnbi.utils.tools.Constants;
import org.cnbi.utils.xml.XMLUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 工程初始类 Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年3月19日上午8:50:11
 */
public class WebApplicationInitializer implements ServletContextListener {
	private final static Log logger = LogFactory.getLog(WebApplicationInitializer.class);

	private ServletContext servletContext;
	/**
	 * 获取spring注入的bean对象
	 */
	private WebApplicationContext springContext;

	public IHandleService handleService;// dashboardService

	private Map<String, String> sqlMap = new HashMap<String, String>();

	private Map<String, List<UtilDim>> dimMap = new HashMap<String, List<UtilDim>>();

	private String projectName;

	private String realPath;

	/**
	 * 系统
	 */
	public void contextDestroyed(ServletContextEvent contextEvent) {
		logger.info("【" + projectName + "】系统已经成功销毁了！");
	}

	public void contextInitialized(ServletContextEvent contextEvent) {
		this.servletContext = contextEvent.getServletContext();// sqlXmpName
		projectName = servletContext.getInitParameter("cnbi");
		String sqlXmpName = servletContext.getInitParameter(Constants.XMLSQLBEAN);
		realPath = this.servletContext.getRealPath(File.separator);
		springContext = WebApplicationContextUtils.getWebApplicationContext(contextEvent.getServletContext());
		handleService = (IHandleService) springContext.getBean("handleService"); // dashboardService
		initSql(sqlXmpName);
		initCompanyData();// ON_LINE_USERS
		// Map<String, HttpSession> onLineUser = new HashMap<String,
		// HttpSession>();
		// servletContext.setAttribute(Constants.ON_LINE_USERS, onLineUser);
		queryMenuList();
		initDimData();
		initSubjectData();
		checkLicenseInvalid();
		logger.info("【" + projectName + "】系统已经成功启动了！并且也成功连接到数据库源！工程路径==" + realPath);

	}

	/*
	 * public String checkUserLicenseLastTime(Properties props){ String sql =
	 * sqlMap.get(Constants.INIT+Constants.LICENSE); String errorMsg = ""; try {
	 * 
	 * @SuppressWarnings("unchecked") List<SysLog> list = (List<SysLog>)
	 * handleService.query(sql, new SysLog());
	 * logger.info("共有日志信息----------》"+list.size()); for(SysLog log:list){ long
	 * lastLoginTime = log.getLoginTime().getTime(); long now = new
	 * Date().getTime(); if (lastLoginTime > now){ errorMsg=
	 * "服务器授权文件已经过期，请与本公司联系！\n" + "联系电话:" + props.getProperty("info.telephone")
	 * + "\n"; break; } } } catch (Exception e) { throw new
	 * RuntimeException("系统启动时查询日志失败！\n"+e); } return errorMsg; }
	 */
	private void checkLicenseInvalid() {
		String sql = sqlMap.get(Constants.INIT + Constants.LICENSE);
		LicenseUtil license = new LicenseUtil(sql, "properties/companyInfo");
		MsgBean msg = license.getMsgBean();
		if (StringUtil.isBlank(msg.getText())) {
			try {
				String errorMsg = "", phone = license.getProps().getProperty("info.outtime");
				if (handleService.queryUserLicenseLastTimeWhetherPastdue(sql)) {
					errorMsg = license.getProps().getProperty("info.outtime");
				}
				msg.setText(errorMsg + phone);
				logger.info("成功读取license文件！" + license.getUseingDate());
			} catch (Exception e) {
				logger.error("查询license文件是否已过期时出错了！\n" + e.getMessage());
			}
		}
		servletContext.setAttribute(Constants.LICENSE_CHECK_INFO, license.getMsgBean());
	}

	/**
	 * 初始所有维度数据
	 * 
	 * @Author 龚佳新
	 * @Time 2014年3月21日下午6:12:09
	 */
	private void initDimData() {
		String dimSql = sqlMap.get(Constants.INIT + Constants.DIM), dimFieldSql = sqlMap.get(Constants.INIT + Constants.DIM + Constants.FIELD);
		String raplaceStr = "DW_Dim";
		try {
			@SuppressWarnings("unchecked")
			List<UtilDim> dimDatas = (List<UtilDim>) handleService.query(dimSql, new UtilDim());
			for (int i = 0, len = dimDatas.size(); i < len; i++) {// factTable
				UtilDim mapBean = dimDatas.get(i);
				String factTable = mapBean.getScode(), sql = sqlMap.get(Constants.INIT + Constants.ITEM);
				String fieldKey = factTable.replace(raplaceStr, "");
				sql = sql.replace(":factTable", factTable);

				if (factTable.equals("DW_DimBranch")) {
					sql = "SELECT scode, sname, sregion as spcode FROM DW_DimBranch";
				}
				@SuppressWarnings("unchecked")
				List<UtilDim> dimList = (List<UtilDim>) handleService.query(sql, new UtilDim());
				dimMap.put(fieldKey, dimList);
			}
			servletContext.setAttribute(Constants.DIM + Constants.MAP, dimMap);

			@SuppressWarnings("unchecked")
			List<UtilDim> dimFieldDatas = (List<UtilDim>) handleService.query(dimFieldSql, new UtilDim());
			Map<String, String> dimFieldMap = new HashMap<String, String>();
			for (int i = 0, len = dimFieldDatas.size(); i < len; i++) {// factTable
				UtilDim mapBean = dimFieldDatas.get(i);
				dimFieldMap.put(mapBean.getScode(), mapBean.getSname());
			}
			servletContext.setAttribute(Constants.DIM + Constants.FIELD + Constants.MAP, dimFieldMap);
		} catch (Exception e) {
			logger.error("初始initDimData所有维度数据时出错了！\n" + e);
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 初始所有主题维度数据
	 */
	private void initSubjectData() {
		String subKey = Constants.INIT + Constants.SUBJECT + Constants.LIST, comKey = Constants.INIT + Constants.COMPOSE + Constants.LIST, subSql = sqlMap.get(subKey), comSql = sqlMap.get(comKey);// ,dimItemDatasSql
																																																	// =
																																																	// sqlMap.get(Constants.INIT+Constants.ITEM);
		Map<String, SubjectSqlBean> subjectMap = new HashMap<String, SubjectSqlBean>();
		// String filePath = realPath
		// +"resources"+File.separator+"json"+File.separator;

		try {//
			@SuppressWarnings("unchecked")
			List<Subject> subjectList = (List<Subject>) handleService.query(subSql, new Subject("0001", "所有主题", "0"));
			for (int i = 0, len = subjectList.size(); i < len; i++) {
				Subject subject = subjectList.get(i);
				@SuppressWarnings("unchecked")
				List<Compose> composeList = (List<Compose>) handleService.query(comSql, new Compose(subject.getScode()));
				Map<String, String> measureMap = null;//
				Map<String, Compose> compseMap = null;
				for (int j = 0, lj = composeList.size(); j < lj; j++) {
					Compose compose = composeList.get(j);
					String type = compose.getType();// 类型
					if (type.equalsIgnoreCase(Constants.MEASURE)) {// 度量
						if (null == measureMap) {// 有节约资源哦 亲！能不new的就坚决不new
							measureMap = new TreeMap<String, String>(new Comparator<String>() {// //
																								// 降序排序obj2.compareTo(obj1);
										public int compare(String obj1, String obj2) {
											return obj1.compareTo(obj2);
										}
									});
						}
						measureMap.put(compose.getField(), compose.getSname());
					} else if (type.equalsIgnoreCase(Constants.SUB)) {// SUBJECT
																		// 主题

					} else {// 维度
						if (null == compseMap) {// 原理同上
							compseMap = new HashMap<String, Compose>();
						}
						compose.setDimDatas(dimMap.get(compose.getField()));
						compseMap.put(compose.getField(), compose);
					}
				}
				if (null != compseMap && null != measureMap) {
					SubjectSqlBean subjectBean = new SubjectSqlBean(subject, compseMap, measureMap);
					subjectMap.put(subject.getScode(), subjectBean);
					// logger.info(subject.getSname() + "【" + subject.getScode()
					// + "】议题SQL====\n" + subjectBean.getSql());
					// if (subject.getScode().equals("1010")) {
					// System.out.println(subjectBean.getSql(new
					// DisplayBean(true,true)));
					// querySubjectData(subjectBean,filePath+subject.getScode()+"22.json");
					// }
				}
			}
			servletContext.setAttribute(Constants.SUBJECT + Constants.MAP, subjectMap);
		} catch (Exception e) {
			logger.error("初始初始所有维度数据时出错了！\n" + e);
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	/**
	 * 初始化sql语句的xml文件
	 * 
	 * @param filePath
	 *            目录 以分号分割 WEB-INF\classes
	 */
	private void initSql(String filePath){
		filePath = StringUtil.replace(filePath, "classpath:", "").replace("/", "\\");
		filePath = realPath+"WEB-INF\\classes\\"+filePath;
		int index = filePath.lastIndexOf("\\")+1;
		String fileRealPath = filePath.substring(0, index), 
				prefix = filePath.substring(index, filePath.length());
		File [] files = new File(fileRealPath).listFiles();
		String []temp = prefix.split("\\"+Constants.MIDDLELINE);
		String splitFileName = temp[0]+Constants.MIDDLELINE;
		boolean flag = false;
		if(temp.length == 2 && temp[1].equals(Constants.STAR)){
			flag = true;
		} 
		for(int i=0,len = files.length;i<len;i++){
			String fileName = files[i].getName(), keyName = fileName.replace(".xml", "");
			if(prefix.length() == 0 || prefix.equals(Constants.STAR)|| flag == true || fileName.contains(prefix)){
				if(flag && !fileName.contains(splitFileName)){}else{
					    XmlSqlBean beans = XMLUtils.getConfigXmlBean(files[i]);
						XMLUtils.getConfigXmlMap(sqlMap, beans);
						servletContext.setAttribute(keyName, beans);
						logger.info("加载了"+fileName+"文件>>"+beans.getName());
				}
				 
			}
		}
		servletContext.setAttribute(Constants.SQLMAP, sqlMap);
	
	}

	/**
	 * @param filePath  老的方法
	 */
//	private void initSqls(String filePath) {
//		try {
//			String files[] = filePath.split("\\" + Constants.SEMICOLON);
//			for (int a = 0, len = files.length; a < len; a++){
//				String fileName = files[a], keyName = fileName.replace(".xml", "");
//				XmlSqlBean beans = XMLUtils.getConfigXmlBean(fileName);
//				XMLUtils.getConfigXmlMap(sqlMap, beans);
//				servletContext.setAttribute(keyName, beans);
//				logger.info("加载了"+fileName+"文件>>"+beans.getName());
//			}
//			servletContext.setAttribute(Constants.SQLMAP, sqlMap);
//		} catch (Exception e) {
//			logger.error("初始sql语句时出错了！\n" + e);
//			throw new RuntimeException(e.getMessage(), e.getCause());
//		}
//	}

	/**
	 * 查询所有菜单
	 * 
	 * @return
	 */
	private void queryMenuList() {
		String key = Constants.MENU + Constants.LIST;
		String sql = sqlMap.get(key);
		try {
			@SuppressWarnings("unchecked")
			List<Menu> menuList = (List<Menu>) handleService.query(sql, new Menu());
			servletContext.setAttribute(key, menuList);
		} catch (Exception e) {
			logger.error("查询所有菜单时出错了！\n" + sql + "--\n" + e);
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	// private void queryDashboardList(){
	// String key = Constants.DASH + Constants.LIST;
	// String sql = sqlMap.get(key);
	// try {
	// List<Dashboard> dashList = dashboardService.queryAllDashboard(sql, new
	// Dashboard());
	// servletContext.setAttribute(key, dashList);
	// } catch (Exception e) {
	// logger.error("查询所有dashboard时出错了！---->\n" + sql + "\n" + e);
	// e.printStackTrace();
	// throw new RuntimeException(e.getMessage(), e.getCause());
	// }
	// }
	/**
	 * 查询所有模型
	 * 
	 * @return scode, sname,spcode,isleaf,isactive,state
	 */
	// private void queryDashboardTree() {
	// String key = Constants.DASH + Constants.BEAN;
	// String sql = sqlMap.get(key);
	// try {
	// // Class.forName(Dashboard.class);
	// // Class c = Class.forName(“A”);factory =
	// // (AInterface)c.newInstance();
	// Dashboard ss = new Dashboard("0", "所有模型", "", 0, 0);
	// // ss = (Dashboard)Class.newInstance();
	// Dashboard bean = dashboardService.queryDashboardTree(sql, ss);
	// servletContext.setAttribute(key, bean);
	// } catch (Exception e) {
	// logger.error("查询所有菜单时出错了！---->\n" + sql + "\n" + e);
	// throw new RuntimeException(e.getMessage(), e.getCause());
	// }
	// }

	/**
	 * 初始所有公司
	 */
	private void initCompanyData() {
	}
}
