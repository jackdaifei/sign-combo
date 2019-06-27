package com.fly.utils;

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
public class HttpUtils {

    public static String postResponse(String url, List<NameValuePair> paramList) {
        return postResponse(url, paramList, null);
    }

    public static String postResponse(String url, List<NameValuePair> paramList, Header[] headers) {
        return postResponse(url, paramList, headers, null, 0);
    }

    public static String postResponse(String url, List<NameValuePair> paramList, Header[] headers, String proxyUrl, int proxyPort) {
        try {
            HttpPost httpPost = new HttpPost(url);
            if (CollectionUtils.isNotEmpty(paramList)) {
                httpPost.setEntity(new UrlEncodedFormEntity(paramList));
            }
            if (ArrayUtils.isNotEmpty(headers)) {
                httpPost.setHeaders(headers);
            }
            RequestConfig requestConfig;
            if (StringUtils.isNotBlank(proxyUrl) && proxyPort > 0) {
                requestConfig = buildDefaultConfig(proxyUrl, proxyPort);
            } else {
                requestConfig = buildDefaultConfig();
            }

            CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse response = client.execute(httpPost);

            String responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
            response.close();
            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public static String getResponse(String url, Header[] headers) {
        return getResponse(url, headers, null, 0);
    }

    public static String getResponse(String url, Header[] headers, String proxyUrl, int proxyPort) {
        try {
            HttpGet httpGet = new HttpGet(url);
            if (ArrayUtils.isNotEmpty(headers)) {
                httpGet.setHeaders(headers);
            }

            RequestConfig requestConfig;
            if (StringUtils.isBlank(proxyUrl) && proxyPort > 0) {
                requestConfig = buildDefaultConfig();
            } else {
                requestConfig = buildDefaultConfig(proxyUrl, proxyPort);
            }

            CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            CloseableHttpResponse response = client.execute(httpGet);
            String responseStr = EntityUtils.toString(response.getEntity(), "utf-8");
            response.close();
            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    private static RequestConfig buildDefaultConfig() {
        return buildDefaultConfig(5000, 5000, 5000, null);
    }

    private static RequestConfig buildDefaultConfig(String ip, int port) {
        return buildDefaultConfig(5000, 5000, 5000, new HttpHost(ip, port));
    }

    private static RequestConfig buildDefaultConfig(int sockTimeout, int connectionTimeout, int connectionRequestTimeout, HttpHost httpHost) {
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
