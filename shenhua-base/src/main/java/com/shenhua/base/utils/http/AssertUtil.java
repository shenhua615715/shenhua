package com.shenhua.base.utils.http;



import com.shenhua.base.exception.ServiceException;
import com.shenhua.base.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

/**
 * @author liuye
 * @date 2018/6/17 0:29
 * @Description: ${todo}
 */
public class AssertUtil {
    private static final Logger log = LoggerFactory.getLogger(AssertUtil.class);
    private static final String ERR_MSG = "参数错误！";

    public static void notNull(Object obj, String message) {
        if (null == obj) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void notNull(Object obj) {
        notNull(obj, ERR_MSG);
    }

    public static void notEmpty(String str, String message) {
        if (StringUtils.isEmpty(str)) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void notEmpty(String str) {
        notEmpty(str, ERR_MSG);
    }

    public static void notEmpty(Collection<?> c) {
        notEmpty(c, ERR_MSG);
    }

    public static void notEmpty(Map<?, ?> c) {
        notEmpty(c, ERR_MSG);
    }

    public static void notEmpty(Map<?, ?> c, String message) {
        notNull(c, message);
        if (c.isEmpty()) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void notEmpty(Collection<?> c, String message) {
        notNull(c, message);
        if (c.size() == 0) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void isTrue(boolean boolVal, String message) {
        if (!boolVal) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void isTrue(boolean boolVal) {
        isTrue(boolVal, ERR_MSG);
    }

    public static void isNotTrue(boolean boolVal, String message) {
        if (boolVal) {
            log.error(message);
            throw new ServiceException(message);
        }
    }

    public static void isNotTrue(boolean boolVal) {
        isNotTrue(boolVal, ERR_MSG);
    }

    /**
     * <p>抛出异常</p>
     *
     * @param msg
     * @author liuye
     * @since [2017年10月8日]
     */
    public static void error(String msg) {
        ServiceException e = new ServiceException(msg);
        log.error(msg, e);
        throw e;
    }

    public static void errorIfIntfFail(Map<String, Object> intfResult, String sysCode, String methodName) {
        if (isRespFail(intfResult)) {
            IntfReqException e = new IntfReqException(String.valueOf(intfResult.get("respCode")), StringUtils.replaceHtml((String) intfResult.get("respDesc")),
                    sysCode, methodName);
            log.error(e.toString(), e);
            throw e;
        }
    }

    /**
     * <p>抛出接口异常</p>
     *
     * @param msg
     */
    public static void errorIntf(String msg, String sysCode, String methodName) {
        IntfReqException e = new IntfReqException(StringUtils.replaceHtml(msg), sysCode, methodName);
        log.error(e.toString(), e);
        throw e;
    }

    /**
     * <p>判断返回消息是否为失败</p>
     * @param resp
     * @return
     */
    public static boolean isRespFail(RespInfo<?> resp) {
        return null == resp || ItfConstant.RESULT_FAIL.equals(resp.getRespCode());
    }

    /**
     * <p>判断返回消息是否为失败</p>
     *
     * @param params
     * @return
     */
    public static boolean isRespFail(Map<String, Object> params) {
        return !isRespSuccess(params);
    }

    /**
     * <p>判断返回消息是否为成功</p>
     *
     * @param resp
     * @return
     */
    public static boolean isRespSuccess(RespInfo<?> resp) {
        return null != resp && ItfConstant.RESULT_SUCCESS.equals(resp.getRespCode());
    }

    /**
     * <p>判断返回消息是否为成功</p>
     *
     * @param params
     * @return
     */
    public static boolean isRespSuccess(Map<String, Object> params) {
        return null != params && ItfConstant.RESULT_SUCCESS.equals(params.get("respCode"));
    }
}
