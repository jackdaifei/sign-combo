package com.fly.utils;

import com.fly.model.MyHeader;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class HeaderUtils {

    public static Header[] extHeaderArray(MyHeader myHeader) {
        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader("Connection", myHeader.getConnection()));
        headerList.add(new BasicHeader("Cookie", myHeader.getCookie()));
        headerList.add(new BasicHeader("Charset", myHeader.getCharset()));
        headerList.add(new BasicHeader("Accept-Encoding", myHeader.getAcceptEncoding()));
        headerList.add(new BasicHeader("jdc-backup", myHeader.getJdcBackup()));
        headerList.add(new BasicHeader("Cache-Control", myHeader.getCacheControl()));
        headerList.add(new BasicHeader("Content-Type", myHeader.getContentType()));
        headerList.add(new BasicHeader("Host", myHeader.getHost()));
        headerList.add(new BasicHeader("User-Agent", myHeader.getUserAgent()));

        Header[] headers = new Header[headerList.size()];
        return headerList.toArray(headers);
    }


}
