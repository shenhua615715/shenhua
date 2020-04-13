package com.shenhua.base.utils.http;

import org.springframework.stereotype.Component;

/**
 * @auther: liuye
 * @date: 2019/11/18 14:18
 * @description:接口类常量
 */
@Component
public class ItfConstant {
    //socket超时时长
    public final static int ITF_SOCKET_TIME_OUT = 30000;
    //http超时时长
    public final static int ITF_HTTP_TIME_OUT = 30000;
    public final static String ITF = "APP";
    public final static String INTF_IN = "I";
    public final static String INTF_OUT = "O";
    /** 公共业务接口返回结果标识， 1：成功，0：失败 */
    public final static String RESULT_FAIL = "0";
    public final static String RESULT_SUCCESS = "1";

    //APP
    public final static String appId = "wxe6e6ed692d5a5cc3";
    public final static String secret = "29d191133a2d4ef96ccafd201cd1d0b0";
    public final static String grantType = "authorization_code";
    public final static String wxAuthUrl = "https://api.weixin.qq.com/sns/jscode2session";
}
