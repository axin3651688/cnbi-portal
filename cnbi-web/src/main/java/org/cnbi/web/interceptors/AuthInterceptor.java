package org.cnbi.web.interceptors;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.cnbi.entity.User;
import org.cnbi.utils.tools.Constants;
import org.cnbi.web.utils.ContextHolderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 权限拦截器
 * Copyright © 2014中国经邦. All rights reserved.
 * @Author 龚佳新
 * @Time 2014年4月8日下午4:00:58
 */
public class AuthInterceptor implements HandlerInterceptor {
	 
	private static final Logger logger = Logger.getLogger(AuthInterceptor.class);
	
	private List<String> excludeUrls;

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object objects) throws Exception {
		String requestPath = ContextHolderUtils.getRequestPath(request);// 用户访问的资源地址
		HttpSession session = request.getSession();
		if (excludeUrls.contains(requestPath)){
			return true;
		} else{
			String basePath = ContextHolderUtils.getBasePath();
			Object object = session.getAttribute(Constants.LOGIN_USER);
			User user = object == null ? null : (User) object;
			if (user == null) {
				boolean isAjaxRequest =ContextHolderUtils.isAjaxRequest(request);
				if (isAjaxRequest) {
					// response.sendError(HttpStatus.UNAUTHORIZED.value(),"您已经太长时间没有操作,请刷新页面");
					response.setStatus(911);// 表示session timeout
				} else {
					/**
					 * 如果是非法进入，则提供记住上一次的地址，start---------------
					 */
					String css = request.getParameter("css") == null ? "default" : request.getParameter("css"), scode = request.getParameter("scode") == null ?"default" : request.getParameter("scode");
					session.setAttribute(Constants.OLDURL, "main.cnbi?scode=" + scode);// css.length()
					session.setAttribute(Constants.CSS, css);// css.substring(beginIndex,
					/**
					 * 如果是非法进入，则提供记住上一次的地址，end---------------
					 */
					response.sendRedirect(basePath);
				}
				logger.info("AJAX==" + isAjaxRequest + ">>>>IP为【" + request.getRemoteAddr() + "】的游客没有登录就试图访问！被拦截了！");
				return false;
			}
			return true;
		}
	}
	
	/**
	 * 转发
	 * @param user
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forword")
	public ModelAndView forword(HttpServletRequest request) {
		return new ModelAndView(new RedirectView("loginController.do?login"));
	}
	/*private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("webpage/login/timeout.jsp").forward(request, response);
	}*/
}