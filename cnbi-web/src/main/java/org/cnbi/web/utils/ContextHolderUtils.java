package org.cnbi.web.utils;
import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cnbi.entity.User;
import org.cnbi.utils.pojo.SubjectSqlBean;
import org.cnbi.utils.pojo.UtilDim;
import org.cnbi.utils.string.StringUtil;
import org.cnbi.utils.tools.Constants;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;

public class ContextHolderUtils {
	
	
	/**
	 * 判断是否为Ajax请求
	 * @param request HttpServletRequest
	 * @return 是true, 否false
	 */
	public static  boolean isAjaxRequest(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");
		boolean isAjaxRequest = requestType != null && requestType.equals("XMLHttpRequest");
		return isAjaxRequest;
	}
	
	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		String str = request.getQueryString();
		if(null != str){
			requestPath=requestPath+"?" +str;
		}
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	public static String getSqlByKey(String key) {
		Map<String, String> sqlMap = (Map<String, String>) getServletContext().getAttribute(Constants.SQLMAP);
		return sqlMap.get(key);
	}

	public static void setContentType(HttpServletResponse response, String type) {
		if (StringUtil.isBlank(type))
			type = "text/html;charset=UTF-8";
		response.setContentType(type);
	}

	public static ServletContext getServletContext() {
		WebApplicationContext web = ContextLoader.getCurrentWebApplicationContext();
		ServletContext context = web.getServletContext();
		return context;
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

	}

	public static User getCurrentUser() {
		User user = (User) getSession().getAttribute(Constants.LOGIN_USER);
		return user;
	}

	public static SubjectSqlBean getSubjectSqlBeanByKey(String key) {
		return getSubjectMap().get(key);
	}

	public static String getDimNameByKey(String key) {
		return getDimFieldMap().get(key);
	}

	public static Map<String, String> getDimFieldMap() {
		return (Map<String, String>) getServletContext().getAttribute(Constants.DIM + Constants.FIELD + Constants.MAP);
	}

	public static Map<String, SubjectSqlBean> getSubjectMap() {
		return (Map<String, SubjectSqlBean>) getServletContext().getAttribute(Constants.SUBJECT + Constants.MAP);
	}

	public static List<UtilDim> getDimListByKey(String key) {
		return getDimMap().get(key);
	}

	public static List<UtilDim> getDimListByKeyAndSpcode(String key, String spcode) {
		List<UtilDim> list = getDimMap().get(key);
		List<UtilDim> result = new ArrayList<UtilDim>();
		for (UtilDim dim : list) {
			if (dim.getSpcode().equals(spcode)) {
				result.add(dim);
			}
		}
		return result;
	}

	public static Map<String, List<UtilDim>> getDimMap() {
		return (Map<String, List<UtilDim>>) getServletContext().getAttribute(Constants.DIM + Constants.MAP);
	}

	/**
	 * 获取HttpServletResponse
	 * 
	 * @deprecated
	 * 
	 **/
	public static HttpServletResponse getResponse() {
		return ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();

	}

	public static String getBasePath() {
		HttpServletRequest request = getRequest();
		String path = request.getContextPath(), basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		return basePath;
	}

	public static String getResourcesPath() {

		return getBasePath() + "resources" + File.separator;// +"json"+File.separator;
	}

	public static void setContext(ModelAndView view) {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		view.addObject("request", request);
		view.addObject("ctx", getBasePath());
		view.addObject("application", getServletContext());
		String css = (String) session.getAttribute(Constants.CSS);
		if (null == css) {
			css = "default";
		}
		String tempCss = request.getParameter("css");
		if (null != tempCss && !tempCss.equals("")) {
			session.setAttribute(Constants.CSS, tempCss);
		} else {
			session.setAttribute(Constants.CSS, css);
		}
		view.addObject("session", session);
	}

}
