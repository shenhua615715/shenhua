package com.shenhua.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuye
 * @date 2019/10/31 17:29
 * @Description: 统一异常处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Object error(Exception ex) {
        log.error("系统统一异常:{}",ex);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "系统异常，请联系系统管理员！");
        map.put("code", "500");
        return map;
    }
}
