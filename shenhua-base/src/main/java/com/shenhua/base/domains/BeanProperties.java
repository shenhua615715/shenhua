package com.shenhua.base.domains;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: liuye
 * @Date: 2019/11/18 11:31
 */
@ConfigurationProperties(prefix = "intf.sky.param")
@Component
@Data
public class BeanProperties {
    /*补天接口开始*/
    private String vCrm;
    private String skyUrl;
    private String token;
    private String operName;
    private String operPwd;
    /*补天接口结束*/

    /*资源接口开始*/
    private String rmsUrl;
    /*资源接口结束*/
}
