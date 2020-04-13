/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.shenhua.base.exception;

/**
 * Service层公用的业务异常.
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * @author liuye
 */

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	private Integer code =  9000;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}
	public ServiceException(String code, String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
