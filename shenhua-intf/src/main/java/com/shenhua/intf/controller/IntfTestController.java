package com.shenhua.intf.controller;

import com.shenhua.base.domains.Result;
import com.shenhua.base.eums.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: liuye
 * @Date: 2019/11/25 23:54
 */
@RestController
@Slf4j
@RequestMapping(value="/intf")
public class IntfTestController {
    /**
     * @auther: liuye
     * @date: 2019/11/25 23:55
     * @description:测试方法
     * @param: []
     * @return: com.shenhua.base.domains.Result
     */
    @GetMapping("/say/first/hello.action")
    public Result sayHello(){
        Result result = new Result();
        result.initResult(ResultEnum.SUCCESS);
        return result;
    }
}
