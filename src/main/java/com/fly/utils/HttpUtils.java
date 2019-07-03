package com.fly.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

/**
 * HTTP请求工具
 */
@Slf4j
public class HttpUtils {

    public static String postMethod(String url, List<NameValuePair> paramList) {
        return postMethod(url, paramList, null);
    }

    public static String postMethod(String url, String params) {
        return postMethod(url, params, null);
    }

    public static String postMethod(String url, List<NameValuePair> paramList, Header[] headers) {
        return postMethod(url, paramList, headers, null, 0);
    }

    public static JSONObject postMethodJson(String url, List<NameValuePair> paramList, Header[] headers) {
        String postResponseStr = postMethod(url, paramList, headers, null, 0);
        if (StringUtils.isNotEmpty(postResponseStr)) {
            return JSONObject.parseObject(postResponseStr);
        }
        return null;
    }

    public static String postMethod(String url, String params, Header[] headers) {
        return postMethod(url, params, headers, null, 0);
    }

    public static JSONObject postMethodJson(String url, String params, Header[] headers) {
        String postResponseStr = postMethod(url, params, headers, null, 0);
        if (StringUtils.isNotEmpty(postResponseStr)) {
            return JSONObject.parseObject(postResponseStr);
        }
        return null;
    }

    public static String postMethod(String url, List<NameValuePair> paramList, Header[] headers, String proxyUrl, int proxyPort) {
        StringBuilder paramBuilder = new StringBuilder();
        String params = "";
        if (CollectionUtils.isNotEmpty(paramList)) {
            // 遍历paramList将请求参数转换为String形式
            for (NameValuePair param : paramList) {
                paramBuilder.append(param.getName()).append("=").append(param.getValue());
                paramBuilder.append("&");
            }
            params = paramBuilder.substring(0, paramBuilder.length() - 1);
        }
        return postMethod(url, params, headers, proxyUrl, proxyPort);
    }

    public static String postMethod(String url, String params, Header[] headers, String proxyUrl, int proxyPort) {
        try {
            // 构建HttpPost
            HttpPost httpPost = buildHttpHost(url, params, headers);
            // 执行请求
            String responseStr = postExecute(httpPost, proxyUrl, proxyPort);
            log.info("postMethod response --->>> {}", responseStr);
            return responseStr;
        } catch (Exception e) {
            log.error("postMethod error --->>> url={}, params={}, headers={}, proxyUrl={}, proxyPort={}", url, params, JSON.toJSON(headers), proxyUrl, proxyPort, e);
        }
        return null;
    }

    public static JSONObject postMethodJson(String url, List<NameValuePair> paramList, Header[] headers, String proxyUrl, int proxyPort) {
        String postResponseStr = postMethod(url, paramList, headers, proxyUrl, proxyPort);
        if (StringUtils.isNotEmpty(postResponseStr)) {
            return JSONObject.parseObject(postResponseStr);
        }
        return null;
    }

    private static String postExecute(HttpPost httpPost, String proxyUrl, int proxyPort) throws Exception {
        // 构建请求配置
        RequestConfig requestConfig = buildRequestConfig(proxyUrl, proxyPort);
        // 构建HttpClient
        CloseableHttpClient client = buildHttpClient(requestConfig);
        // 执行请求
        String responseStr = executeRequest(client, httpPost);
        log.info("postExecute response --->>> {}", responseStr);
        return responseStr;
    }

    /**
     * 构建HttpPost
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws Exception
     */
    private static HttpPost buildHttpHost(String url, String params, Header[] headers) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotBlank(params)) {
            httpPost.setEntity(new StringEntity(params));
        }
        if (ArrayUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers);
        }
        return httpPost;
    }









    public static String getMethod(String url, Header[] headers) {
        return getMethod(url, headers, null, 0);
    }

    public static String getMethod(String url, Header[] headers, String proxyUrl, int proxyPort) {
        try {
            // 构建HttpGet
            HttpGet httpGet = buildHttpGet(url, headers);
            // 构建请求配置
            RequestConfig requestConfig = buildRequestConfig(proxyUrl, proxyPort);
            // 构建HttpClient
            CloseableHttpClient client = buildHttpClient(requestConfig);
            // 执行请求
            String responseStr = executeRequest(client, httpGet);
            log.info("get response --->>> {}", responseStr);
        } catch (Exception e) {
            log.error("getMethod error --->>> url={}, headers={}, proxyUrl={}, proxyPort={}", url, JSON.toJSON(headers), proxyUrl, proxyPort, e);
        }
        return null;
    }

    public static JSONObject getMethodJson(String url, Header[] headers, String proxyUrl, int proxyPort) {
        String getResponseStr = getMethod(url, headers, proxyUrl, proxyPort);
        if (StringUtils.isNotEmpty(getResponseStr)) {
            return JSONObject.parseObject(getResponseStr);
        }
        return null;
    }

    /**
     * 构建HttpGet
     * @param url
     * @param headers
     * @return
     */
    private static HttpGet buildHttpGet(String url, Header[] headers) {
        HttpGet httpGet = new HttpGet(url);
        if (ArrayUtils.isNotEmpty(headers)) {
            httpGet.setHeaders(headers);
        }
        return httpGet;
    }












    /**
     * 构建HttpClient
     * @param requestConfig
     * @return
     */
    private static CloseableHttpClient buildHttpClient(RequestConfig requestConfig) {
        return HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    /**
     * 执行请求并获取字符串形式返回结果
     * @param client
     * @param httpUriRequest
     * @return
     * @throws Exception
     */
    private static String executeRequest(CloseableHttpClient client, HttpUriRequest httpUriRequest) throws Exception {
        // 执行请求
        CloseableHttpResponse response = client.execute(httpUriRequest);
        // 获取请求结果
        String responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
        response.close();
        return responseStr;
    }

    /**
     * 绕过HTTPS验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    /**
     * 构建请求配置
     * @return
     */
    private static RequestConfig buildRequestConfig() {
        return buildRequestConfig(5000, 5000, 5000, null);
    }

    private static RequestConfig buildRequestConfig(String proxyUrl, int proxyPort) {
        HttpHost httpHost = null;
        if (StringUtils.isBlank(proxyUrl) && proxyPort > 0) {
            httpHost = new HttpHost(proxyUrl, proxyPort);
        }
        return buildRequestConfig(5000, 5000, 5000, httpHost);
    }

    private static RequestConfig buildRequestConfig(int sockTimeout, int connectionTimeout, int connectionRequestTimeout, HttpHost httpHost) {
        RequestConfig.Builder builder = RequestConfig.custom()
                .setSocketTimeout(sockTimeout)
                .setConnectTimeout(connectionTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setStaleConnectionCheckEnabled(true);
        if (null != httpHost) {
            builder.setProxy(httpHost);
        }

        return builder.build();
    }

}
