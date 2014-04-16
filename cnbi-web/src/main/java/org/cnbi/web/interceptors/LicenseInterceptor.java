package org.cnbi.web.interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cnbi.utils.pojo.MsgBean;
import org.cnbi.utils.tools.Constants;
import org.cnbi.web.utils.ContextHolderUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * 
 * @Author 龚佳新
 * @Time 2014年4月1日下午5:10:22 license拦截器
 */
public class LicenseInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 在controller后拦截
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception) throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 在controller前拦截
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		MsgBean errorMsg = (MsgBean)ContextHolderUtils.getServletContext().getAttribute(Constants.LICENSE_CHECK_INFO);
		boolean flag = false;
		if (errorMsg.getFlag() == false){
			flag = true;
		}else{
			request.setAttribute("errorMsg",errorMsg.getText());
			request.getRequestDispatcher("/noLicense.jsp").forward(request, response);
		}
		return flag;
	}

	
}