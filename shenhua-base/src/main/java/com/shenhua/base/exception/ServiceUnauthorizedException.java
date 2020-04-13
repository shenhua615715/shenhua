package com.shenhua.base.exception;

/**
 * 自定义401无权限异常(UnauthorizedException)
 * @author Wang926454
 * @date 2018/8/30 13:59
 */
public class ServiceUnauthorizedException extends RuntimeException {

    public ServiceUnauthorizedException(String msg){
        super(msg);
    }

    public ServiceUnauthorizedException() {
        super();
    }
}
