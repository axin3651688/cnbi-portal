package org.cnbi.utils.exception;


/**
 * 
 * Copyright © 2014中国经邦. All rights reserved.
 * @author 龚佳新
 *
 * @date 2014年4月10日下午6:27:46
 */
public class ExceptionUtils {

	private ExceptionUtils(){
		//no instance
	}
	
	
	/**
	 * 如果目标为空则抛出异常
	 * 2009-11-3
	 * @param target
	 * @param errorMessage
	 */
	public static void throwIfNull(Object target,String errorMessage){
		if(target==null){
			throw new BusinessException(errorMessage);
		}
	}
	
	/**
	 * 如果目标为空则抛出异常
	 * 本方法空指针安全
	 * 2009-11-3
	 * @param target
	 * @param errorMessage
	 */
	public static void throwIfEmpty(String target,String errorMessage)
	{
		if(target==null || target.equals("")){
			throw new BusinessException(errorMessage);
		}
	}
	
}
