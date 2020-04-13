package com.shenhua.base.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuye
 * @date 2018/6/16 23:57
 * @Description: ${todo}
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     * UTF-8编码
     */
    private static final String UTF8 = "UTF-8";
    /**
     * GBK编码
     */
    private static final String GBK = "GBK";

    /**
     * 设置以JSON格式请求
     */
    private static final String REQUEST_AS_JSON = "application/json";
    private static final String REQUEST_AS_FORM = "application/x-www-form-urlencoded";
    private static int socketTimeout = ItfConstant.ITF_SOCKET_TIME_OUT;
    private static int connectTimeout = ItfConstant.ITF_HTTP_TIME_OUT;
    private static RequestConfig config = RequestConfig.custom().setSocketTimeout(socketTimeout)
            .setConnectTimeout(connectTimeout).build();

    /**
     * post方式请求url，返回结果
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url, Object params) throws Exception {
        return post(url, params, null, UTF8);
    }

    /**
     * post方式请求url，返回结果
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> keyMap, Object params) throws Exception {
        return post(url, params, keyMap, UTF8);
    }

    /**
     * post方式请求url，返回结果
     *
     * @param url
     * @param params
     * @param params  接受String和Map类型的参数
     * @param charset 指定返回字符编码
     * @return
     * @throws Exception
     */
    public static String post(String url, Object params, Map<String, String> keyMap, String charset) throws Exception {

        String responseBody = null;
        final CloseableHttpClient httpclient = url.startsWith("https") ? createSSLClientDefault() : HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        if (params instanceof String) {
            StringEntity se = new StringEntity((String) params, UTF8);
            se.setContentType(REQUEST_AS_JSON);
            httppost.setEntity(se);
        } else {
            httppost.setEntity(new UrlEncodedFormEntity(getPostParam(params), UTF8));
        }
        httppost.setHeader("Referer", url);
        if (keyMap != null) {
            for (Map.Entry<String, String> entry : keyMap.entrySet()) {
                httppost.addHeader(entry.getKey(), entry.getValue());
            }
        }
        httppost.setConfig(config);
        try {
            responseBody = httpclient.execute(httppost, getResponseHandler(charset));
        } finally {
            httpclient.close();
        }
        return responseBody;

    }

    /**
     * get方式请求url，返回结果
     *
     * @param url
     * @param params 接受String和Map类型的参数
     * @return
     * @throws Exception
     */
    public static String get(String url, Object params) throws Exception {
        return get(url, params, UTF8);
    }

    /**
     * get方式请求url，返回结果
     *
     * @param url
     * @param params
     * @param params  接受String和Map类型的参数
     * @param charset 指定返回字符编码
     * @return
     * @throws Exception
     */
    public static String get(String url, Object params, String charset) throws Exception {
        String responseBody = null;
        final CloseableHttpClient httpclient = url.startsWith("https") ? createSSLClientDefault() : HttpClients.createDefault();
        if (params instanceof String) {
            url = concatGetUrl(url, (String) params);
        } else {
            url = concatGetUrl(url, EntityUtils.toString(new UrlEncodedFormEntity(getPostParam(params), UTF8)));
        }
        //logger.debug("url---" + url);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Referer", url);
        httpGet.setConfig(config);
        try {
            responseBody = httpclient.execute(httpGet, getResponseHandler(charset));
        } finally {
            httpclient.close();
        }
        //logger.debug("responseBody---" + responseBody);
        return responseBody;

    }

    /**
     * <p>拼接get方法的Url</p>
     *
     * @param url
     * @param params
     */
    private static String concatGetUrl(String url, String params) {
        int questPos = url.indexOf("?");
        int lastIdx = url.length() - 1;
        StringBuffer result = new StringBuffer(url);
        if (questPos == -1) {
            result.append("?").append(params);
        } else if (questPos == lastIdx) {
            result.append(params);
        } else {
            result.append("&").append(params);
        }
        return result.toString();
    }

    /**
     * <p>
     * 根据参数类型区分获取参数的结果
     * </p>
     * <p>
     * 增加参数可直接为List#NameValuePair#类型
     * </p>
     *
     * @param params 查询参数
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<NameValuePair> getPostParam(Object params) {
        if (params instanceof List) {
            return (List<NameValuePair>) params;
        } else if (params instanceof Map) {
            return getPostParam((Map<String, Object>) params);
        }
        logger.warn("Unrecognized param type {}, use empty param list.", params);
        return new ArrayList<NameValuePair>();
    }

    /**
     * <p>
     * 获取请求参数
     * </p>
     * <p>
     * 修改记录：<br/>
     * 1. 修改内容：增加兼容list/object[]类型参数传递，传递同名参数
     * </p>
     *
     * @param params
     * @return
     */
    public static List<NameValuePair> getPostParam(Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (params != null) {
            Object value = null;
            for (String key : params.keySet()) {
                value = params.get(key);
                if (value instanceof Object[]) {
                    for (Object obj : (Object[]) value) {
                        nvps.add(new BasicNameValuePair(key, getString(obj)));
                    }
                } else if (value instanceof List) {
                    for (Object obj : (List<?>) value) {
                        nvps.add(new BasicNameValuePair(key, getString(obj)));
                    }
                } else {
                    nvps.add(new BasicNameValuePair(key, getString(value)));
                }
            }
        }
        return nvps;
    }

    private static String getString(Object obj) {
        return null == obj ? "" : obj.toString();
    }

    /**
     * 获取字符编码的handler
     *
     * @param charset
     * @return
     */
    public static ResponseHandler<String> getResponseHandler(String charset) {
        ResponseHandler<String> handler = ResponseHandlerSet.CHARSET_HANDLER.get(charset);
        handler = null == handler ? new CharsetResponseHandler(charset) : handler;
        return handler;
    }

    /**
     * 响应处理器集合
     */
    @SuppressWarnings("serial")
    public interface ResponseHandlerSet {

        /**
         * GBK响应处理器
         */
        ResponseHandler<String> GBK_HANDLER = new CharsetResponseHandler(GBK);

        /**
         * UTF-8响应处理器
         */
        ResponseHandler<String> UTF8_HANDLER = new CharsetResponseHandler(UTF8);

        /**
         * 响应处理器与字符集对应关系
         */
        Map<String, ResponseHandler<String>> CHARSET_HANDLER = new HashMap<String, ResponseHandler<String>>() {
            {
                put(GBK, GBK_HANDLER);
                put(UTF8, UTF8_HANDLER);
            }
        };

    }


    /**
     * 创建ssl client
     *
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();

            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            sslContext.init(null, new TrustManager[]{tm}, null);

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new TrustAnyHostnameVerifier());
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    private static class TrustAnyHostnameVerifier
            implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            return true;
        }

        private TrustAnyHostnameVerifier() {
        }

    }

}

class CharsetResponseHandler implements ResponseHandler<String> {

    private String charset;

    public CharsetResponseHandler(String charset) {
        this.charset = charset;
    }

    @Override
    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
        HttpEntity entity = response.getEntity();
        String responseBody = entity != null ? EntityUtils.toString(entity, this.charset) : null;
        int status = response.getStatusLine().getStatusCode();
        if (status >= 200 && status <= 300) {
            return responseBody;
        } else if (status == 301 || status == 302) {
            return "错误：发生了重定向";
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status + "\nresponseBody:" + responseBody);
        }
    }

}

