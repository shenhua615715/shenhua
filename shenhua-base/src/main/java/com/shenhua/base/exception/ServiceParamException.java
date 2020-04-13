/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.shenhua.base.exception;

/**
 * Service层公用的参数异常.
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * @author liuye
 */
public class ServiceParamException extends RuntimeException {

	private static final long serialVersionUID = 3583566093089790852L;

	private Integer code = 8888;

	public ServiceParamException() {
		super();
	}

	public ServiceParamException(String message) {
		super(message);
	}
	public ServiceParamException(String code, String message) {
		super(message);
	}

	public ServiceParamException(Throwable cause) {
		super(cause);
	}

	public ServiceParamException(String message, Throwable cause) {
		super(message, cause);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
