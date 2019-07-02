package com.fly.model;

import lombok.Data;

@Data
public class MyHeader {

    private String host;
    private String connection;
    private String accept;
    private String origin;
    private String userAgent;
    private String contentType;
    private String referer;
    private String acceptEncoding;
    private String acceptLanguage;
    private String cookie;
    private String xRequestedWith;
    private String charset;
    private String jdcBackup;
    private String cacheControl;

}
