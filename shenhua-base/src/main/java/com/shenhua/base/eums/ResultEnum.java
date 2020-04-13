package com.shenhua.base.eums;

/**
 * Created by liuye on 2019/3/7.
 */
public enum ResultEnum {
    SUCCESS(0,"操作成功"),
    ERROR_TOKEN(-1,"身份校验错误"),
    ERROR_PARAM(-2,"参数错误"),
    ERROR_REQUEST(-3,"请求错误"),
    ERROR_TIME_OUT(-4,"请求超时"),
    /**
     * 通用报错code和msg
     */
    SERVICE_ERROR(9000,"服务异常"),
    SYSTEM_ERROR(9999,"系统异常");

    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
