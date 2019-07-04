package com.fly.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.model.MyHeader;
import com.fly.utils.CommonUtils;
import com.fly.utils.HeaderUtils;
import com.fly.utils.HttpUtils;
import org.apache.http.Header;

public class JDFinance {

    public static void main(String[] args) throws Exception {
        String cookie = "pt_key=app_openAAJdHWBCADABVNq_K7IJQJIyhgj139UdG8x2ErycSTAkh4TswOYMWqSiPP71dkFt4bXHlPMj3X8; pt_pin=jackdaifei_m; pwdt_id=jackdaifei_m; sid=9f6fb43ea65fc7f0b8865a155488465w; qd_ad=-%7C-%7Cdirect%7C-%7C0; _jrda=1; _jrdb=1562206305826; qd_sid=JXO1A4MR-POT5Q7RPDV0JYA319MHT-1; qd_uid=JXO1A4MR-POT5Q7RPDV0JYA319MHT; qd_sq=1; qd_fs=1562206275303; qd_ls=1562206275303; qd_ts=1562206275303";
        while (true) {
            String cookie2 = "_jrda=1; __jdv=168871293|direct|-|none|-|1562208629276; __jrr=7FC769449914345E2D67DF05EA753D; _jrdb=1562208635093; __jda=168871293.15622086292741313592771.1562208629.1562208629.1562208629.1; __jdb=168871293.2.15622086292741313592771|1.1562208629; __jdc=168871293; __jdu=15622086292741313592771; pt_key=app_openAAJdHWlzADDFDiufPknjQvDPw9Q53buUxn-TNh3H6dg2_A3LDUEnHSDsrkKfZ4BoZ3y-nlDQ0XM; pt_pin=jackdaifei_m; pwdt_id=jackdaifei_m; sid=3ab3bb0f5ab2823a2542712ed5e4740w; 3AB9D23F7A4B3C9B=KWVSHNUO4MB3VTPWRM3MFPRQVSARY54O3KPOQT5W66NHNZBHJDDRC4HGJ62D6KPCDVIVY2DNZNQTJCE5IWRW5GJFYE; qd_ad=-%7C-%7Cdirect%7C-%7C0; qd_sid=JXO2OKHM-U4XIMIWKCHPO3K1V06HW-1; qd_uid=JXO2OKHM-U4XIMIWKCHPO3K1V06HW; qd_sq=1; qd_fs=1562208628659; qd_ls=1562208628659; qd_ts=1562208628659";
            harvest(cookie2);
            int sleepNum = CommonUtils.randomNum(280000, 360000);
            Thread.sleep(sleepNum);
        }
        // baseSignInEncryptNew(cookie);
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

    private static void getMsgAdPageDataService(String cookie) {
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
            }
        }

    }

    private static void sendAdGb(String cookie, String adId) {
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
                } else {
                    System.out.println(resultData.getString("msg"));
                }
            }
        }
    }

    private static void conOrderLottery(String cookie) {
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
                    oneNeedUserPickUpReward(cookie);
                }
            }
        }
    }

    private static void oneNeedUserPickUpReward(String cookie) {
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
