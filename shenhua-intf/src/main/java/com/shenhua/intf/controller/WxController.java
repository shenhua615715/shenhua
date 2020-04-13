package com.shenhua.intf.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shenhua.base.domains.Result;
import com.shenhua.base.entity.Authorization;
import com.shenhua.base.eums.ResultEnum;
import com.shenhua.base.utils.ResultUtil;
import com.shenhua.base.utils.StringUtils;
import com.shenhua.base.utils.http.HttpClientUtil;
import com.shenhua.base.utils.http.ItfConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.protocol.HttpRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: liuye
 * @Date: 2019/11/25 23:54
 */
@RestController
@Slf4j
@RequestMapping(value = "/wx")
public class WxController {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * @auther: liuye
     * @date: 2019/11/25 23:55
     * @description:测试方法
     * @param: []
     * @return: com.shenhua.base.domains.Result
     */
    @GetMapping("/say/first/hello.action")
    public Result sayHello() {
        Result result = new Result();
        result.initResult(ResultEnum.SUCCESS);
        return result;
    }

    @GetMapping("/getAuthorization.action")
    public JSONObject getAuthorization(@RequestParam  String code) {
        logger.debug("code------------"+code);
        String _authorization ="";
        JSONObject authorization  = new JSONObject ();
        String wxAuthUrl = ItfConstant.wxAuthUrl;
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("appId", ItfConstant.appId);
        params.put("secret", ItfConstant.secret);
        params.put("js_code",code);
        params.put("grant_type", "authorization_code");
        try {
            _authorization =  HttpClientUtil.get(wxAuthUrl, params);
            logger.debug("_authorization----------" + _authorization);
            if (StringUtils.isNotBlank(_authorization))
                authorization = JSONObject.parseObject(_authorization);
            logger.debug("111111----------" + authorization);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorization;
    }

}
