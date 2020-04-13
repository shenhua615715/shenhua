package com.shenhua.base.utils.constant;

/**
 * @author liuye
 * @date 2019/3/12 16:12
 * @Description: 常量
 */
public class Constant {

    /***********************通用常量开始****************************/
    /**
     * redis-OK
     */
    public final static String OK = "OK";

    /**
     * redis过期时间，以秒为单位，一分钟
     */
    public final static int EXRP_MINUTE = 60;

    /**
     * redis过期时间，以秒为单位，一小时
     */
    public final static int EXRP_HOUR = 60 * 60;

    /**
     * redis过期时间，以秒为单位，一天
     */
    public final static int EXRP_DAY = 60 * 60 * 24;

    /**
     * redis-key-前缀-shiro:cache:
     */
    public final static String PREFIX_SHIRO_CACHE = "shiro:cache:";

    /**
     * redis-key-前缀-shiro:access_token:
     */
    public final static String PREFIX_SHIRO_ACCESS_TOKEN = "shiro:access_token:";

    /**
     * redis-key-前缀-shiro:refresh_token:
     */
    public final static String PREFIX_SHIRO_REFRESH_TOKEN = "shiro:refresh_token:";

    /**
     * JWT-account:
     */
    public final static String ACCOUNT = "staffId";

    /**
     * JWT-currentTimeMillis:
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * PASSWORD_MAX_LEN
     */
    public static final Integer PASSWORD_MAX_LEN = 8;

    //文件下载配置
    public final static String FILE_TYPE_EXCEL = "file_type_excel";//excel类型
    public final static String FILE_EXCEL_PATH = "/templates/modules/";//excel文件下载目录
    public final static String FILE_EXCEL_PATH_BATCH = "/templates/modules/";//excel文件下载目录
    /***********************通用常量结束****************************/


    /***********************post常量开始****************************/
    //神话
    public final static String SYS_post_EXP_STATE_CODE = "100001";
    public final static String SYS_post_EXP_STATE = "未配置状态表";

    public final static String SYS_post_EXP_SUB_CODE = "100002";
    public final static String SYS_post_EXP_SUB = "未配置处理方法";

    public final static String SYS_post_EXP_DEAL_CODE = "100003";
    public final static String SYS_post_EXP_DEAL = "未配置脚本或存储过程";

    public final static String SYS_post_EXP_PROC_CODE = "100004";
    public final static String SYS_post_EXP_PROC = "存储过程未配置参数";

    //订单、服务类
    public final static String SYS_post_EXP_TRADE = "110001";
    public final static String SYS_post_EXP_TRADE_ERRMESS = "未找到订单";

    public final static String SYS_post_EXP_BPMERRLOG = "110002";
    public final static String SYS_post_EXP_BPMERRLOG_MESS = "日志表无记录";

    public final static String SYS_post_EXP_USER = "120001";
    public final static String SYS_post_EXP_USER_ERRMESS = "获取用户资料无记录";

    public final static String SYS_post_EXP_PAYRELATION = "120002";
    public final static String SYS_post_EXP_PAYRELATION_ERRMESS = "获取付费关系异常";

    //调用补天接口异常
    public final static String SYS_post_EXP_SKY_ERRCODE = "130001";
    public final static String SYS_post_EXP_SKY_ERRMESS = "调用补天接口异常";

    //调用一证五户接口异常
    public final static int SYS_post_EXP_ONEFIVE_ERRCODE = 140001;
    public final static String SYS_post_EXP_ONEFIVE_ERRMESS = "调用一证五户接口异常";

    public final static int SYS_post_EXP_ONEFIVE_MISS = 140002;
    public final static String SYS_post_EXP_ONEFIVE_MISSMESS = "未配置一证五户接口";

    public final static int SYS_post_EXP_ONEFIVE_TIMEOUT = 140003;
    public final static String SYS_post_EXP_ONEFIVE_TIMEOUTMESS = "调用一证五户接口超时";


    /***********************post常量结束****************************/


    /***********************ACT常量开始****************************/


    /***********************ACT常量结束****************************/
}
