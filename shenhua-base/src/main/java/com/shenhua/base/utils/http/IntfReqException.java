package com.shenhua.base.utils.http;


import com.shenhua.base.exception.ServiceBusiException;

/**
 * <p>接口请求异常类，在接口请求中抛出的异常使用此异常</p>
 */
public class IntfReqException extends ServiceBusiException {
    /** */
    private static final long serialVersionUID = 1L;

    private String respCode;

    private String respDesc;

    private String methodName;

    private String sysCode;

    public IntfReqException() {
        super();
    }
    public IntfReqException(String message){
        super(message);
    }

    public IntfReqException(String message, String sysCode, String methodName, Throwable cause) {
        super(message, cause);
        this.respCode = ItfConstant.RESULT_FAIL;
        this.respDesc = message;
        this.sysCode = sysCode;
        this.methodName = methodName;
    }

    public IntfReqException(String errorCode, String message, String sysCode, String methodName, Throwable cause) {
        super(message, cause);
        this.respCode = errorCode;
        this.respDesc = message;
        this.sysCode = sysCode;
        this.methodName = methodName;
    }

    public IntfReqException(String message, String sysCode, String methodName) {
        super(message);
        this.respCode = ItfConstant.RESULT_FAIL;
        this.respDesc = message;
        this.sysCode = sysCode;
        this.methodName = methodName;
    }

    public IntfReqException(String errorCode, String message, String sysCode, String methodName) {
        super(message);
        this.respCode = errorCode;
        this.respDesc = message;
        this.sysCode = sysCode;
        this.methodName = methodName;
    }

    public IntfReqException(Throwable cause, String sysCode, String methodName) {
        super();
        this.respCode = ItfConstant.RESULT_FAIL;
        this.respDesc = (cause instanceof IntfReqException) ? ((IntfReqException)cause).getRespDesc() : cause.getMessage();
        this.sysCode = sysCode;
        this.methodName = methodName;
    }

    /**
     * @return the respCode
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * @param respCode the respCode to set
     */
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    /**
     * @return the respDesc
     */
    public String getRespDesc() {
        return respDesc;
    }

    /**
     * @param respDesc the respDesc to set
     */
    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    /**
     * @return the methodName
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * @param methodName the methodName to set
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    /**
     * @return the sysCode
     */
    public String getSysCode() {
        return sysCode;
    }

    /**
     * @param sysCode the sysCode to set
     */
    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getMessage() {
        return new StringBuilder().append("错误码：").append(respCode)
                .append("<br/>").append("错误详情：").append(respDesc).append("<br/>").append("接口信息：[")
                .append(sysCode).append("]").append(methodName).toString();
    }

    @Override
    public String toString() {
        return "IntfException [respCode=" + respCode + ", respDesc=" + respDesc + ", methodName=" + methodName + ", sysCode="
                + sysCode + "]";
    }
    
    
}
