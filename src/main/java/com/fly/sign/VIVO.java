package com.fly.sign;

import com.alibaba.fastjson.JSONObject;
import com.fly.model.MyHeader;
import com.fly.utils.HeaderUtils;
import com.fly.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;

@Slf4j
public class VIVO {

    public static void main(String[] args) {
        String cookie = "vvc_an=9; vvc_q=820640e7-76c6-458d-8857-42e69cb62311; vvc_imei=868602047125517; vvc_p=15928073559; vvc_model=vivo+Z1; vvc_s=2%7C4030970382; vvc_r=sys_NzA3ZTBlYzc0ZDZjZTA5Y2I4YTguMzAzNTg1MDY2LjE1MzU0Mzg1MTM1MDA; vvc_cs=0; vvc_has=1; vvc_app_version=2201; vvc_u=150100335636434d420242b7da136500; vvc_openid=94bd10e822a03a3d; vvc_pn=com.bbk.appstore; vvc_elapsedtime=170645118; vvc_av=28; vvc_status=1; vvc_k=bbddbffa2c989bf8993594d16ed7e590; vivo_point_cookie_openid=94bd10e822a03a3d";
        sign(cookie);
    }

    private static void sign(String cookie) {
        String url = "https://pointh5.vivo.com.cn/api/sign/start.do";

        Header[] headers = builderHeader(cookie);
        String params = "{\"ticket\":\"\",\"pts_sys_ver\":\"20190624_v4.1.2\"}";

        JSONObject responseJson = HttpUtils.postMethodJson(url, params, headers);

        if (null != responseJson) {
            int retcode = responseJson.getIntValue("retcode");
            if (retcode == 0) {
                log.info("VIVO sign success --->>> {}", responseJson);
                return;
            }
        }
        log.error("VIVO sign error --->>> {}", responseJson);
    }

    private static Header[] builderHeader(String cookie) {
        MyHeader myHeader = new MyHeader();
        myHeader.setHost("pointh5.vivo.com.cn");
        myHeader.setConnection("keep-alive");
        myHeader.setAccept("application/json, text/plain, */*");
        myHeader.setOrigin("https://pointh5.vivo.com.cn");
        myHeader.setUserAgent("Mozilla/5.0 (Linux; Android 9; vivo Z1 Build/PKQ1.180819.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/71.0.3578.83 Mobile Safari/537.36");
        myHeader.setContentType("application/json;charset=UTF-8");
        myHeader.setReferer("https://pointh5.vivo.com.cn/");
        myHeader.setAcceptEncoding("gzip, deflate");
        myHeader.setAcceptLanguage("zh-CN,en-US;q=0.9");
        myHeader.setCookie(cookie);
        myHeader.setXRequestedWith("com.bbk.appstore");

        return HeaderUtils.extHeaderArray(myHeader);
    }

}
