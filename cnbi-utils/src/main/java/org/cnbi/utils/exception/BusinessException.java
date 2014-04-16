package org.cnbi.utils.exception;
/**
 * 
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 *
 * @date 2014年4月10日下午6:30:32
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause)
	{
		super(cause);
	}
	
	public BusinessException(String message,Throwable cause)
	{
		super(message,cause);
	}
}
