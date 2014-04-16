/**
 * 
 */
package org.cnbi.web.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 * @date 2014年4月10日下午7:31:13
 * @cnbi-web
 */
public class BaseController {
	/** 基于@ExceptionHandler异常处理 */
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		
		// 根据不同错误转向不同页面
		/*if(ex instanceof BusinessException) {
			return "error-business";
		}else if(ex instanceof ParameterException) {
			return "error-parameter";
		} else {
			return "error";
		}*/
		return "error";
	}
}
