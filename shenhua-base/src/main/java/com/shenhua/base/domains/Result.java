package com.shenhua.base.domains;

import com.shenhua.base.eums.ResultEnum;

/**
 * http请求返回的最外层对象
 * Created by liuye on 2019/3/7.
 */
public class Result<T> {

    private Integer code;
    private String msg;
    private Long count = 0L;
    private T data;

    public Result() {
        super();
        this.code = ResultEnum.ERROR_REQUEST.getCode();
        this.msg = ResultEnum.ERROR_REQUEST.getMsg();
    }
    public Result(int code,String msg){
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public void initResult(ResultEnum resultEnum){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
