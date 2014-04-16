package org.cnbi.web.exception;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.cnbi.utils.exception.ExceptionUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 * @date 2014年4月10日下午6:32:05
 * @cnbi-web
 */
@Component
public class MyExceptionHandler implements HandlerExceptionResolver {

	private static final Logger logger = Logger.getLogger(MyExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex) {
		String exceptionMessage = ExceptionUtil.getExceptionMessage(ex);
		logger.error(exceptionMessage);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		model.put("ex", ex);
		return new ModelAndView("common/error", model);
	}
}
