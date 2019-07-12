package com.fly.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.model.MyHeader;
import com.fly.utils.CommonUtils;
import com.fly.utils.HeaderUtils;
import com.fly.utils.HttpUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class JDFinance {

    public static void main(String[] args) throws Exception {
        String cookie = "pt_key=app_openAAJdJwVIADDqHakphFl7I_vTmCHvGWTWHHDQWWJgzmDWJT4yWC7rNiyac62cN4o5HSTksjLAnLs; pt_pin=jackdaifei_m; pwdt_id=jackdaifei_m; sid=7cffd139f77c046d11121ef0f2e6801w; 3AB9D23F7A4B3C9B=KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE; qd_ad=-%7C-%7Cdirect%7C-%7C0; __jrr=70F995A748C486B61DD1533331B925; qd_sid=JXYHLKWP-BXKDRV629I5LDXSU94PS-1; qd_uid=JXYHLKWP-BXKDRV629I5LDXSU94PS; qd_sq=1; qd_fs=1562838345238; qd_ls=1562838345238; qd_ts=1562838345238";
        String cookie2 = "pt_key=app_openAAJdJwVIADDqHakphFl7I_vTmCHvGWTWHHDQWWJgzmDWJT4yWC7rNiyac62cN4o5HSTksjLAnLs; pt_pin=jackdaifei_m; pwdt_id=jackdaifei_m; sid=7cffd139f77c046d11121ef0f2e6801w; 3AB9D23F7A4B3C9B=KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE; qd_ad=-%7C-%7Cdirect%7C-%7C0; __jrr=70F995A748C486B61DD1533331B925; qd_sid=JXYHLKWP-BXKDRV629I5LDXSU94PS-1; qd_uid=JXYHLKWP-BXKDRV629I5LDXSU94PS; qd_sq=1; qd_fs=1562838345238; qd_ls=1562838345238; qd_ts=1562838345238";
        String cyqCookie = "qd_fs=1562295448483; qd_ls=1562295448483; qd_sid=JXPIDF38-KK2Q9MT85YBK29L1X4EE-2; qd_sq=2; qd_ts=1562304725086; qd_uid=JXPIDF38-KK2Q9MT85YBK29L1X4EE; 3AB9D23F7A4B3C9B=THUSPLXJOLG5IVLWW2XQEG5BEA4D6K6ZVDPWE6WDHQPUUT4QHUVKXWYCI6VI47XRJBHCYGQS2UNWFDDQ7LS4UBLTOU; __jdu=1394363494; __jda=168871293.1394363494.1527779319.1562296586.1562304757.10; __jdb=168871293.1.1394363494|10.1562304757; __jdc=168871293; _jrda=2; _jrdb=1562304757264; __jdv=168871293|uuj.jr.jd.com|-|referral|-|1562296586362; TrackerID=b_bYny-PcnJtEzxmwEnwBjBw18346WHPLgMMtGwVCvGWB0Jj_4FD08a3IM8JNVFJEHcR1cbHQEKP5N_boA_Mt6AGs4ZvxXYl5Qmn7DKOw6W7G1FG8QAmiMKZkQEksg7aHJzd7Ni90OqHEzAB7LNwBQ; pt_key=AAJdHryfADCa-ZOUfnkF-Bcw5SiWPSfKQj5m4gxg1jVQa4pST5NYNA0PaOU7ZnYCV0r-Tfn7qWU; pt_pin=u_4b33eb17f380e; pt_token=fqi9zhgx; pwdt_id=u_4b33eb17f380e; lsid=2zgyvccm3miekbz11e874gbo68v5sgwu; qd_ad=Android*url*1562295354072%7CQxr/JBE5XkHTVhQMFDTgAg%7Cwechatshare%7C-%7C10; __wga=1560750333633.1560750333633.1553526907038.1538225212726.1.7; sc_width=375; shshshfp=9762d2001a3460e7172c4d284a3c37b5; shshshfpb=1fa0716df1d364649b68aaf4d20168fe70e820d253d24616d5baf743ef; wq_uits=%7Cal%2C2%7Cug%2C1; cid=1; retina=1; open_id=oTGnpnNKPd70DZUn7Kd1DYy3QApw; wq_unionid=oCwKwuH6CN78wUNDMDGSXZmynVzQ; wxmall_ptype=1; __jrr=0307BEA07B2F6EBC9881CD8470410B; mba_muid=1394363494; shshshfpa=c9b81101-5bff-ed39-49e0-e644520062f1-1538225213; visitkey=21197374540969021; webp=0";
        String jhCookie = "unpl=V2_YzNtbUcCEBd8DEJXcxpbBGICQF4RBEsXdwxHBnobWwRgCkdYclRCFX0UR1NnGlUUZwoZXktcRxFFCEJkexhdBWILG1xLVXMlfQAoVDYZMgYJA18QD2dAFUUIR2R7EVQAYQASXUdRcyV1DnZkKE0EWCNBSwUDDxxDRQl2VUsYbE4JAl9dSl9GE3YIRlF9KV01ZA%3d%3d; retina=1; cid=1; webp=1; mba_muid=1994721147; __wga=1561717738860.1561717738860.1561717738860.1561717738860.1.1; sc_width=424; shshshfp=5c25cfbe3d8baf5335a63f3b09a07ad7; shshshfpa=ab613ded-3456-37bb-bd84-56ff606b2be7-1561717739; shshshfpb=kkfLloAERWwtgpwIspORk3A%3D%3D; qd_ad=androidapp%7Ct_335139774%7Cappshare%7CWxfriends%7C10; qd_uid=JXPIB6NQ-I5P13N0HLDC07RZVKQ1P; qd_fs=1562295344230; qd_ls=1562295344230; __jrr=C36DE5A1C5F0AAEF158B3A61F0452A; guid=f67aa55b08f09da4531330d2c106bf10a05c9be25afd7fbb9c704a0bdc9af094; lsid=ujz0erewav08kr34t2sv60gyyhzkpcfk; TrackerID=NM8l9rDzLRLZhGOYr9t89Kv70xvCoXmzgFylZu3fJP6urkCDupt8zxzHnN_6TG-no54oz778yDu4YeH52wHuniC5nluJVYiGBCq01zeY8gHekYvhqUkn5zervHaY0Chf; pt_key=AAJdHrw4ADDUfSMjBq0AKi8ORWqd-1riMQnMzYYdpo-V1gEB01KORdvX1xMmad3bwtU-nBEHKAY; pt_pin=shaoye1213; pt_token=xhbwyubm; pwdt_id=shaoye1213; __jda=168871293.1994721147.1561717734.1561717738.1562295974.3; __jdc=168871293; __jdv=168871293|uuj.jr.jd.com|-|referral|-|1562295974168; _jrda=1; __jdu=1994721147; qd_ts=1562307756778; qd_sq=2; qd_sid=JXPIB6NQ-I5P13N0HLDC07RZVKQ1P-2; 3AB9D23F7A4B3C9B=2LMUKYFN6BADPOOMY6QDEOO5YB7CFHJUAAATFUZBZTFH4EX5KH5EVVFYEVWGHHUB3AYEDTYSJFGWC5XIZKGCL64CBA";
        // while (true) {
        //     harvest(cookie2);
        //     int sleepNum = CommonUtils.randomNum(72000, 3600000);
        //     System.out.println("sleep" + sleepNum/1000/60 + "m");
        //     Thread.sleep(sleepNum);
        // }
        // signBeanIndex(cookie);
        // signIn(cookie);
        // Thread.sleep(CommonUtils.randomNum(3000, 20000));
        // getSignAwardJR(cookie);
        // Thread.sleep(CommonUtils.randomNum(5000, 20000));
        // baseSignInEncryptNew(cookie);
        // Thread.sleep(CommonUtils.randomNum(3000, 20000));
        // getMsgAdPageDataService(cookie2);
        // Thread.sleep(CommonUtils.randomNum(8000, 20000));
        // conOrderLottery(cookie2);
        // Thread.sleep(CommonUtils.randomNum(5000, 20000));
        // huaxiangangbeng(cookie2);
    }

    /**
     * JD APP 签到
     * @param appCookie
     */
    public static void signBeanIndex(String appCookie) {
        String signResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=signBeanIndex&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1561631287686&sign=42662f04125e8c118ebad9e49ee6d7c3&sv=120";
            String reqBody = "{\"fp\":\"-1\",\"jda\":\"-1\",\"monitor_refer\":\"\",\"monitor_source\":\"bean_app_bean_index\",\"referUrl\":\"-1\",\"rnVersion\":\"4.0\",\"shshshfp\":\"-1\",\"shshshfpa\":\"-1\",\"userAgent\":\"-1\"}";
            Header[] appHeaders = builderJDAppHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);
            JSONObject signJson = HttpUtils.postMethodJson(url, paramList, appHeaders);
            if (null != signJson) {
                String code = signJson.getString("code");
                if ("0".equals(code)) {
                    JSONObject data = signJson.getJSONObject("data");
                    JSONObject dailyAward = data.getJSONObject("dailyAward");
                    System.out.print("APP签到--------->>>");
                    if (null != dailyAward) {
                        System.out.println(dailyAward.getString("title") + dailyAward.getString("subTitle") + dailyAward.getJSONObject("beanAward").getString("beanCount") + "京豆");
                    } else {
                        JSONObject continuityAward = data.getJSONObject("continuityAward");
                        System.out.println(continuityAward.getString("title") + continuityAward.getJSONObject("beanAward").getString("beanCount") + "京豆");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(signResult);
            e.printStackTrace();
        }
    }

    private static Header[] builderJDAppHeader(String cookie) {
        MyHeader myHeader = new MyHeader();
        myHeader.setConnection("Keep-Alive");
        myHeader.setCookie(cookie);
        myHeader.setCharset("UTF-8");
        myHeader.setAcceptEncoding("gzip,deflate");
        myHeader.setJdcBackup(cookie);
        myHeader.setCacheControl("no-cache");
        myHeader.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        myHeader.setHost("api.m.jd.com");
        myHeader.setUserAgent("okhttp/3.6.0");
        return HeaderUtils.extHeaderArray(myHeader);
    }

    private static List<NameValuePair> buildParamList(String body) {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("body", body));
        return paramList;
    }



    private static void signIn(String cookie) {
        String url = "https://ms.jr.jd.com/gw/generic/hy/h5/m/signIn?_=" + System.currentTimeMillis();
        String params = "reqData={\"channelSource\":\"JRAPP\"}";
        Header[] appHeaders = builderHeader(cookie);
        JSONObject signJson = HttpUtils.postMethodJson(url, params, appHeaders);
        if (null != signJson) {
            int resultCode = signJson.getIntValue("resultCode");
            if (resultCode == 0) {
                System.out.print("金融签到--------->>>");
                JSONObject resultData = signJson.getJSONObject("resultData");
                JSONObject resBusiData = resultData.getJSONObject("resBusiData");
                System.out.println("[" + resBusiData.getFloat("thisAmount") / 100 + "]钢镚");
            }
        }
    }

    private static void getSignAwardJR(String cookie) {
        // String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/getSignAwardJR?_=1562207903499";
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/getSignAwardJR?_=" + System.currentTimeMillis();
        String params = "reqData={\"riskDeviceParam\":\"{\\\"deviceType\\\":\\\"vivo Z1\\\",\\\"traceIp\\\":\\\"\\\",\\\"macAddress\\\":\\\"20F77C733FA1\\\",\\\"imei\\\":\\\"868602047125517\\\",\\\"os\\\":\\\"android\\\",\\\"osVersion\\\":\\\"9\\\",\\\"fp\\\":\\\"98663d444a7709d6ef08b3f693592aca\\\",\\\"ip\\\":\\\"10.12.199.75\\\",\\\"eid\\\":\\\"KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE\\\",\\\"appId\\\":\\\"com.jd.jrapp\\\",\\\"openUUID\\\":\\\"\\\",\\\"uuid\\\":\\\"868602047125517-20F77C733FA1\\\",\\\"clientVersion\\\":\\\"5.2.32\\\",\\\"resolution\\\":\\\"1080.0*2201.0\\\",\\\"channelInfo\\\":\\\"vivo#309080027\\\",\\\"networkType\\\":\\\"wifi\\\",\\\"startNo\\\":\\\"732\\\",\\\"openid\\\":\\\"\\\",\\\"token\\\":\\\"\\\",\\\"sid\\\":\\\"\\\",\\\"terminalType\\\":\\\"02\\\",\\\"longtitude\\\":\\\"\\\",\\\"latitude\\\":\\\"\\\",\\\"securityData\\\":\\\"\\\",\\\"jscContent\\\":\\\"\\\",\\\"fnHttpHead\\\":\\\"\\\",\\\"receiveRequestTime\\\":\\\"\\\",\\\"port\\\":\\\"\\\",\\\"appType\\\":3,\\\"optType\\\":\\\"\\\",\\\"idfv\\\":\\\"\\\",\\\"wifiSSID\\\":\\\"\\\",\\\"wifiMacAddress\\\":\\\"\\\",\\\"cellIpAddress\\\":\\\"\\\",\\\"wifiIpAddress\\\":\\\"\\\",\\\"sdkToken\\\":\\\"\\\"}\"}";
        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m.jr.jd.com/integrate/signin/index.html?sid=6aff4fba22704ad880196739b71508fw");
        Header[] headers = builderHeader(myHeader);

        JSONObject signAwardJson = HttpUtils.postMethodJson(url, params, headers);
        System.out.println(signAwardJson);
    }

    private static void baseSignInEncryptNew(String cookie) {
        String url = "https://ms.jr.jd.com/gw/generic/base/h5/m/baseSignInEncryptNew";
        String params = "reqData={}&source=app";
        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m.jr.jd.com/spe/qyy/hzq/index.html?usertype=1176&from=sq&sid=4772ee0b86ac77c37ab0d28e6dc9664w");
        myHeader.setAccept("*/*");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        System.out.println(dataJson);
    }

    private static void harvest(String cookie) {
        // String url = "https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=1562208710662";
        String url = "https://ms.jr.jd.com/gw/generic/uc/h5/m/harvest?_=" + System.currentTimeMillis();
        String params = "reqData={\"source\":2,\"sharePin\":null}";
        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://uuj.jr.jd.com/wxgrowing/moneytree7/index.html?channellv=sq&sid=3ab3bb0f5ab2823a2542712ed5e4740w");
        Header[] headers = builderHeader(myHeader);

        JSONObject harvestJson = HttpUtils.postMethodJson(url, params, headers);
        System.out.println(harvestJson);
    }

    private static void getMsgAdPageDataService(String cookie) throws Exception {
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/getMsgAdPageDataService";
        String params = "reqData={\"clientType\":\"android\",\"actKey\":\"176696\",\"userDeviceInfo\":{\"env\":\"jrapp\"},\"isclientnew\":\"new\",\"from\":\"qiandao\",\"cu\":\"true\",\"utm_source\":\"kong\",\"utm_medium\":\"tuiguang\",\"utm_campaign\":\"t_1000550368_\",\"utm_term\":\"a5e5b2fefc244d678e78ce047bd7455f\"}";
        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m.jr.jd.com/btyingxiao/advertMoney/html/home.html?from=qiandao&cu=true&utm_source=kong&utm_medium=tuiguang&utm_campaign=t_1000550368_&utm_term=a5e5b2fefc244d678e78ce047bd7455f");
        myHeader.setAccept("*/*");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                JSONArray bannerList = resultData.getJSONArray("bannerList");
                for (int i=0;i<bannerList.size();i++) {
                    JSONObject banner = bannerList.getJSONObject(i);
                    boolean b = sendAdGb(cookie, banner.getString("adId"));
                    if (b) {
                        break;
                    }
                    int sleepNum = CommonUtils.randomNum(10000, 15000);
                    Thread.sleep(sleepNum);
                }
            }
        }

    }

    private static boolean sendAdGb(String cookie, String adId) throws Exception {
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/sendAdGb";
        String params = "reqData={\"clientType\":\"android\",\"actKey\":\"176696\",\"userDeviceInfo\":{\"adId\":" + adId + "},\"deviceInfoParam\":{\"appId\":\"com.jd.jrapp\",\"clientVersion\":\"5.2.32\",\"deviceType\":\"vivo Z1\",\"osPlatform\":\"android\",\"osVersion\":\"9\",\"resolution\":\"1080.0*2201.0\",\"channelInfo\":\"vivo#309080027\",\"networkType\":\"wifi\",\"IPAddress1\":\"10.12.199.75\",\"macAddress\":\"20F77C733FA1\",\"deviceId\":\"868602047125517\",\"UUID\":\"868602047125517-20F77C733FA1\",\"startNo\":\"736\",\"latitude\":\"30.548812\",\"longitude\":\"104.067344\",\"country\":\"中国\",\"province\":\"四川省\",\"city\":\"成都市\",\"terminalType\":\"02\",\"IPAddress\":\"\"},\"bussource\":\"\"}";

        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m.jr.jd.com/btyingxiao/advertMoney/html/home.html?from=qiandao&cu=true&utm_source=kong&utm_medium=tuiguang&utm_campaign=t_1000550368_&utm_term=a5e5b2fefc244d678e78ce047bd7455f");
        myHeader.setAccept("*/*");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                String code = resultData.getString("code");
                if ("0000".equals(code)) {
                    System.out.println(resultData.getString("msg") + ":" + resultData.getJSONObject("data").getIntValue("volumn") + "京豆");
                    return false;
                } else {
                    System.out.println(resultData.getString("msg"));
                    // throw new Exception("已领完");
                }
            }
        }
        return true;
    }

    private static void conOrderLottery(String cookie) throws Exception {
        // String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/conOrderLottery?_=1562219220229";
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/conOrderLottery?_=" + System.currentTimeMillis();
        String params = "reqData={\"actCode\":\"C082E09049\",\"channelLv2\":null,\"riskDeviceParam\":\"{\\\"deviceType\\\":\\\"vivo Z1\\\",\\\"traceIp\\\":\\\"\\\",\\\"macAddress\\\":\\\"20F77C733FA1\\\",\\\"imei\\\":\\\"868602047125517\\\",\\\"os\\\":\\\"android\\\",\\\"osVersion\\\":\\\"9\\\",\\\"fp\\\":\\\"98663d444a7709d6ef08b3f693592aca\\\",\\\"ip\\\":\\\"10.12.199.75\\\",\\\"eid\\\":\\\"KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE\\\",\\\"appId\\\":\\\"com.jd.jrapp\\\",\\\"openUUID\\\":\\\"\\\",\\\"uuid\\\":\\\"868602047125517-20F77C733FA1\\\",\\\"clientVersion\\\":\\\"5.2.32\\\",\\\"resolution\\\":\\\"1080.0*2201.0\\\",\\\"channelInfo\\\":\\\"vivo#309080027\\\",\\\"networkType\\\":\\\"wifi\\\",\\\"startNo\\\":\\\"736\\\",\\\"openid\\\":\\\"\\\",\\\"token\\\":\\\"\\\",\\\"sid\\\":\\\"\\\",\\\"terminalType\\\":\\\"02\\\",\\\"longtitude\\\":\\\"104.067344\\\",\\\"latitude\\\":\\\"30.548812\\\",\\\"securityData\\\":\\\"\\\",\\\"jscContent\\\":\\\"\\\",\\\"fnHttpHead\\\":\\\"\\\",\\\"receiveRequestTime\\\":\\\"\\\",\\\"port\\\":\\\"\\\",\\\"appType\\\":3,\\\"optType\\\":\\\"\\\",\\\"idfv\\\":\\\"\\\",\\\"wifiSSID\\\":\\\"\\\",\\\"wifiMacAddress\\\":\\\"\\\",\\\"cellIpAddress\\\":\\\"\\\",\\\"wifiIpAddress\\\":\\\"\\\",\\\"sdkToken\\\":\\\"\\\"}\"}";

        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m1.jr.jd.com/zc/drawSystem/hb/index.html?showhead=no&cu=true&utm_source=m.jr.jd.com&utm_medium=tuiguang&utm_campaign=t_1000550368_&utm_term=cd1837c6dcd14c35a22d591c89fa4519&contentParam=100001581&act=1&actCode=C082E09049&actType=1&channelLv=shangyehuaduanxin");
        myHeader.setOrigin("https://m1.jr.jd.com");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                boolean success = resultData.getBooleanValue("success");
                if (success) {
                    System.out.println(resultData.getString("msg"));
                    Thread.sleep(CommonUtils.randomNum(500, 1200));
                    oneNeedUserPickUpReward(cookie);
                }
            }
        }
    }

    private static void oneNeedUserPickUpReward(String cookie) throws Exception {
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/oneNeedUserPickUpReward";
        String params = "reqData={\"actCode\":\"C082E09049\"}&source=app";

        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m1.jr.jd.com/zc/drawSystem/hb/index.html?showhead=no&cu=true&utm_source=m.jr.jd.com&utm_medium=tuiguang&utm_campaign=t_1000550368_&utm_term=cd1837c6dcd14c35a22d591c89fa4519&contentParam=100001581&act=1&actCode=C082E09049&actType=1&channelLv=shangyehuaduanxin");
        myHeader.setOrigin("https://m1.jr.jd.com");
        myHeader.setAccept("*/*");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                boolean success = resultData.getBooleanValue("success");
                if (success) {
                    System.out.println(resultData.getString("msg"));
                    Thread.sleep(CommonUtils.randomNum(500, 1200));
                    pickUpReward(cookie);
                }
            }
        }
    }

    private static void pickUpReward(String cookie) {
        // String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/pickUpReward?_=1562219227103";
        String url = "https://ms.jr.jd.com/gw/generic/jrm/h5/m/pickUpReward?_=" + System.currentTimeMillis();
        String params = "reqData={\"actCode\":\"C082E09049\",\"rewardCode\":\"2372\",\"channelLv2\":null,\"riskDeviceParam\":\"{\\\"deviceType\\\":\\\"vivo Z1\\\",\\\"traceIp\\\":\\\"\\\",\\\"macAddress\\\":\\\"20F77C733FA1\\\",\\\"imei\\\":\\\"868602047125517\\\",\\\"os\\\":\\\"android\\\",\\\"osVersion\\\":\\\"9\\\",\\\"fp\\\":\\\"98663d444a7709d6ef08b3f693592aca\\\",\\\"ip\\\":\\\"10.12.199.75\\\",\\\"eid\\\":\\\"KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE\\\",\\\"appId\\\":\\\"com.jd.jrapp\\\",\\\"openUUID\\\":\\\"\\\",\\\"uuid\\\":\\\"868602047125517-20F77C733FA1\\\",\\\"clientVersion\\\":\\\"5.2.32\\\",\\\"resolution\\\":\\\"1080.0*2201.0\\\",\\\"channelInfo\\\":\\\"vivo#309080027\\\",\\\"networkType\\\":\\\"wifi\\\",\\\"startNo\\\":\\\"736\\\",\\\"openid\\\":\\\"\\\",\\\"token\\\":\\\"\\\",\\\"sid\\\":\\\"\\\",\\\"terminalType\\\":\\\"02\\\",\\\"longtitude\\\":\\\"104.067344\\\",\\\"latitude\\\":\\\"30.548812\\\",\\\"securityData\\\":\\\"\\\",\\\"jscContent\\\":\\\"\\\",\\\"fnHttpHead\\\":\\\"\\\",\\\"receiveRequestTime\\\":\\\"\\\",\\\"port\\\":\\\"\\\",\\\"appType\\\":3,\\\"optType\\\":\\\"\\\",\\\"idfv\\\":\\\"\\\",\\\"wifiSSID\\\":\\\"\\\",\\\"wifiMacAddress\\\":\\\"\\\",\\\"cellIpAddress\\\":\\\"\\\",\\\"wifiIpAddress\\\":\\\"\\\",\\\"sdkToken\\\":\\\"\\\"}\"}";

        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://m1.jr.jd.com/zc/drawSystem/hb/index.html?showhead=no&cu=true&utm_source=m.jr.jd.com&utm_medium=tuiguang&utm_campaign=t_1000550368_&utm_term=cd1837c6dcd14c35a22d591c89fa4519&contentParam=100001581&act=1&actCode=C082E09049&actType=1&channelLv=shangyehuaduanxin");
        myHeader.setOrigin("https://m1.jr.jd.com");
        Header[] headers = builderHeader(myHeader);

        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                boolean success = resultData.getBooleanValue("success");
                if (success) {
                    System.out.println(resultData.getString("msg") + resultData.getJSONObject("data").getString("rewardName"));
                }
            }
        }
    }

    /**
     * 划线领钢镚
     * @param appCookie
     * @throws Exception
     */
    private static void huaxiangangbeng(String appCookie) throws Exception {
        String url = "https://coin.jd.com/m/sign/userSign.do";
        Header[] headers = new Header[]{
                new BasicHeader("Host", "coin.jd.com"),
                new BasicHeader("Connection", "keep-alive"),
                new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"),
                new BasicHeader("Origin", "https://coin.jd.com"),
                new BasicHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.1; vivo Z1 Build/OPM1.171019.011; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044325 Mobile Safari/537.36/application=JDJR-App&clientType=android&deviceId=868602047125517&src=vivo&version=5.0.7&clientVersion=5.0.7&osVersion=8.1.0&osName=vivo Z1&isUpdate=0&HiClVersion=5.0.7&netWork=1&netWorkType=1&ip=10.12.197.40&mac=20:F7:7C:73:3F:A1&sPoint=MTAwMDYjIw%3D%3D%0A&*#@jdPaySDK*#@jdPayChannel=jdFinance&jdPayChannelVersion=5.0.7&jdPaySdkVersion=2.21.6.0&androidBrand=vivo&androidManufacturer=vivo&jdPayClientName=Android*#@jdPaySDK*#@"),
                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"),
                new BasicHeader("Referer", "https://coin.jd.com/m/gb/index.html?sid=ec7eca6b943d12a9cafbdf81f83a87fw"),
                new BasicHeader("Accept-Encoding", "gzip, deflate"),
                new BasicHeader("Accept-Language", "zh-CN,en-US;q=0.8"),
                new BasicHeader("Cookie", appCookie),
                new BasicHeader("X-Requested-With", "XMLHttpRequest"),
        };

        String params = "deviceStr={\"type\":43,\"showUrl\":\"https://coin.jd.com/m/gb/index.html?sid=ec7eca6b943d12a9cafbdf81f83a87fw\",\"eid\":\"KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE\",\"fp\":\"9b05a1d501ed5f11eaa2852437f0e082\"}&source=JRMO";

        JSONObject responseJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != responseJson) {
            String resultCode = responseJson.getString("resultCode");
            if ("0000".equals(resultCode)) {
                JSONObject data = responseJson.getJSONObject("data");
                JSONObject reward = data.getJSONObject("reward");
                String volumn = reward.getString("volumn");
                System.out.println("割线签到获取钢镚--------->>>" + volumn);
            } else {
                System.out.println("割线签到获取钢镚失败..." + responseJson);
            }
        }
    }

    /**
     *
     * @param cookie
     * @param opType 1点击分享, 2领取
     */
    private void doWork(String cookie, int opType) throws Exception {
        String url = "https://ms.jr.jd.com/gw/generic/uc/h5/m/doWork?_=" + System.currentTimeMillis();
        String params = "reqData={\"source\":2,\"workType\":2,\"opType\":" + opType + "}";

        MyHeader myHeader = defaultHeader(cookie);
        myHeader.setReferer("https://uuj.jr.jd.com/wxgrowing/moneytree7/index.html?channellv=sy&sid=54ce04c997a81798f51df272478e6fdw");
        myHeader.setOrigin("https://uuj.jr.jd.com");
        Header[] headers = builderHeader(myHeader);
        JSONObject dataJson = HttpUtils.postMethodJson(url, params, headers);
        if (null != dataJson) {
            int resultCode = dataJson.getIntValue("resultCode");
            if (resultCode == 0) {
                JSONObject resultData = dataJson.getJSONObject("resultData");
                String code = resultData.getString("code");
                if ("200".equals(code)) {
                    JSONObject data = resultData.getJSONObject("data");
                    Integer nextStatus = data.getInteger("nextStatus");
                    if (null != nextStatus && nextStatus == 1) {
                        int sleepNum = CommonUtils.randomNum(1500, 8201);
                        Thread.sleep(sleepNum);
                        doWork(cookie, 2);
                    }
                }
            }
        }
    }



    private static MyHeader defaultHeader(String cookie) {
        MyHeader myHeader = new MyHeader();
        myHeader.setHost("ms.jr.jd.com");
        myHeader.setConnection("keep-alive");
        myHeader.setAccept("application/json");
        myHeader.setOrigin("https://m.jr.jd.com");
        myHeader.setUserAgent("Mozilla/5.0 (Linux; Android 9.0; vivo Z1 Build/PKQ1.180819.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044328 Mobile Safari/537.36/application=JDJR-App&clientType=android&deviceId=868602047125517&src=vivo&version=5.2.32&clientVersion=5.2.32&osVersion=9&osName=vivo Z1&isUpdate=0&HiClVersion=&netWork=1&netWorkType=1&ip=10.12.199.75&mac=20:F7:7C:73:3F:A1&sPoint=MTAwMDYjIw%3D%3D%0A&*#@jdPaySDK*#@jdPayChannel=jdFinance&jdPayChannelVersion=5.2.32&jdPaySdkVersion=1.1.3&androidBrand=vivo&androidManufacturer=vivo&jdPayClientName=Android*#@jdPaySDK*#@");
        myHeader.setContentType("application/x-www-form-urlencoded;charset=UTF-8");
        myHeader.setReferer("https://m.jr.jd.com/vip/sign/html/index.html?source=shuangqian&sid=9f6fb43ea65fc7f0b8865a155488465w");
        myHeader.setAcceptEncoding("gzip, deflate");
        myHeader.setAcceptLanguage("zh-CN,en-US;q=0.8");
        myHeader.setCookie(cookie);
        myHeader.setXRequestedWith("com.jd.jrapp");
        return myHeader;
    }

    private static Header[] builderHeader(String cookie) {
        return HeaderUtils.extHeaderArray(defaultHeader(cookie));
    }

    private static Header[] builderHeader(MyHeader myHeader) {
        return HeaderUtils.extHeaderArray(myHeader);
    }
}
