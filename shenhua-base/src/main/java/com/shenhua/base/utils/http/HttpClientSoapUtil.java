package com.shenhua.base.utils.http;

/**
 * @Description:HttpClient Soap工具类
 * @Author: liuye
 * @Date: 2019/12/23 00:32
 */
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.*;


@Slf4j
public class HttpClientSoapUtil{
    /**
     * @auther: liuye
     * @date: 2019/12/23 13:15
     * @description:使用SOAP1.1发送消息,能调用服务端为soap1.1和soap1.2的服务
     * @param: [postUrl, soapXml, soapAction]
     * @return: java.lang.String
     */
    public static String doPostSoap(String postUrl, String soapXml,String soapAction) {
        String retStr = "";
        // 创建HttpClientBuilder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        // HttpClient
        CloseableHttpClient closeableHttpClient = null;

        if(postUrl.startsWith("https")){
            closeableHttpClient = HttpClientUtil.createSSLClientDefault();
        }else{
            closeableHttpClient = httpClientBuilder.build();
        }
        HttpPost httpPost = new HttpPost(postUrl);
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(ItfConstant.ITF_SOCKET_TIME_OUT)          // 请求超时时间
                .setConnectTimeout(ItfConstant.ITF_HTTP_TIME_OUT).build();  // 传输超时时间
        httpPost.setConfig(requestConfig);
        try {
            //使用SOAP1.1发送消息,只能调用1.2
            httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
            //使用SOAP1.2发送消息,只能调用1.2
            //httpPost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
            httpPost.setHeader("SOAPAction", soapAction);
            StringEntity data = new StringEntity(soapXml,Charset.forName("UTF-8"));
            httpPost.setEntity(data);
            CloseableHttpResponse response = closeableHttpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                //响应内容
                retStr = EntityUtils.toString(httpEntity, "UTF-8");
            }
            // 释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            log.error("exception in doPostSoap:{}", e);
        }
        return retStr;
    }



}

