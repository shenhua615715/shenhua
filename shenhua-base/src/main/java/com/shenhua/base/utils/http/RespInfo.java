package com.shenhua.base.utils.http;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuye
 * @date 2018/6/17 0:32
 * @Description: ${todo}
 */
public class RespInfo<T> implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(RespInfo.class);
    private static final long serialVersionUID = 1L;

    private String respCode;
    private String respDesc;
    private String time;

    private T data;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public T getData() {
        return data;
    }

    @SuppressWarnings( { "unchecked", "hiding" })
    public <T> T getData(Class<T> clazz) {
        if (data != null && !clazz.equals(data.getClass())) {
            return JSON.parseObject(data.toString(), clazz);
        }
        return (T) data;
    }

    @SuppressWarnings( { "unchecked", "hiding" })
    public <T> T getList(Class<T> beanClazz) {
        return getList(beanClazz, List.class);
    }

    @SuppressWarnings( { "unchecked", "hiding" })
    public <T> T getList(Class<T> beanClazz, Class<? extends List> listClazz) {
        if (data != null && !listClazz.equals(data.getClass()) && !beanClazz.equals(data.getClass())) {
            List list = JSON.parseArray(data.toString(), beanClazz);
            if (listClazz.equals(List.class) || listClazz.equals(ArrayList.class)) {
                return (T) list;
            }
            List nList = null;
            try {
                nList = (List) listClazz.newInstance();
            } catch (InstantiationException e) {
                log.error(e.getMessage(), e);
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            }
            nList.addAll(list);
            return (T) nList;
        }
        return (T) data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
