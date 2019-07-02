package com.fly.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.utils.CommonUtils;
import com.fly.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class JDSign {


    public static void main(String[] args) {
        String cookie = "pin=jackdaifei_m;wskey=AAFbT9AfAEAWGgHkSanFbpfjKXgOiumMdr6hsmtr2NT8oMdHMGiyza11FEh5pRhZjDKGmIvaBKfLN7WYk7n3dJOtSXVkvGPJ;whwswswws=zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj/545EHilrLsep8ki5XL/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==;unionwsws={\"devicefinger\":\"eidA2B700114ODY4NjAyMDQ3MTI1NTE3MA==0I7u8gZRJfxuaDVhagH3uQjoCRqDZgV4oZflP8e\\/6uphn9NxD4vvpXaqrAFb0+Oog6wilp42RLPGbnAB\",\"jmafinger\":\"zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj\\/545EHilrLsep8ki5XL\\/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==\"};";
//        signBeanIndex(cookie);

        plantBeanIndex(cookie);
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
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            signResult = HttpUtils.postResponse(url, paramList, appHeaders);
            JSONObject signJson = JSONObject.parseObject(signResult);
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

    public static void plantBeanIndex(String appCookie) {
        String plantResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=plantBeanIndex&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1561636588607&sign=6684ff6dee506a6a76bc38ab018efedf&sv=100";
            String reqBody = "{\"followType\":\"1\",\"monitor_refer\":\"\",\"monitor_source\":\"plant_app_plant_index\",\"shareUuid\":\"\",\"wxHeadImgUrl\":\"\"}";
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            plantResult = HttpUtils.postResponse(url, paramList, appHeaders);

            JSONObject plantJson = JSONObject.parseObject(plantResult);
            if (null != plantJson) {
                if ("0".equals(plantJson.getString("code"))) {
                    JSONObject data = plantJson.getJSONObject("data");

                    // 可领取培养液
                    JSONObject timeNutrientsRes = data.getJSONObject("timeNutrientsRes");

                    // 可使用培养液数量
                    int nutrients = 0;
                    String roundId = "";
                    JSONArray roundList = data.getJSONArray("roundList");
                    for (int i=0; i < roundList.size(); i++) {
                        JSONObject round = roundList.getJSONObject(i);
                        if ("2".equals(round.getString("roundState"))) {
                            roundId = round.getString("roundId");
                            nutrients += round.getIntValue("nutrients");
                            break;
                        }
                    }
                    if (StringUtils.isBlank(roundId)) {
                        System.out.println("get Nutrients ERROR");
                    }

                    System.out.println("可使用营养液：" + nutrients);

                    String state = timeNutrientsRes.getString("state");
                    if ("1".equals(state)) {
                        System.out.println("有可领取的营养液！！！");
                        // 随机停几秒
                        Thread.sleep(CommonUtils.sleepMillisecond(3000, 14500));
                        // 获取培养液, 并返回获取结果
                        boolean isSuccess = receiveNutrients(appCookie, roundId);
                        if (!isSuccess) {
                            System.out.println("获取营养液失败...");
                        } else {
                            // 获取成功, 培养液数量+1
                            nutrients += 1;
                            System.out.println("获取营养液成功！！！");
                        }
                    } else {
                        String countDown = timeNutrientsRes.getString("countDown");
                        System.out.println("剩余[" + countDown + "]秒");
                    }

                    // 使用培养液
                    if (nutrients > 0) {
                        Thread.sleep(CommonUtils.sleepMillisecond(3500, 13600));
                        boolean useSuccess = cultureBean(appCookie, roundId);
                        if (!useSuccess) {
                            System.out.println("使用营养液失败...");
                        } else {
                            System.out.println("使用营养液成功！！！");
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(plantResult);
            e.printStackTrace();
        }
    }

    /**
     * 领取营养液
     * @param appCookie
     */
    private static boolean receiveNutrients(String appCookie, String roundId) {
        String receiveResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=receiveNutrients&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1561638112810&sign=c4440ab134543d51873f9b346a3d6b7b&sv=112";
            String reqBody = "{\"monitor_refer\":\"plant_receiveNutrients\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"" + roundId + "\"}";
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            receiveResult = HttpUtils.postResponse(url, paramList, appHeaders);

            JSONObject receiveJson = JSONObject.parseObject(receiveResult);
            if (null != receiveJson) {
                String code = receiveJson.getString("code");
                if ("0".equals(code)) {
                    JSONObject data = receiveJson.getJSONObject("data");
                    if (null != data) {
                        if (data.containsKey("countDown") && data.containsKey("nutrients") && data.containsKey("state")) {
                            System.out.println("领取营养液成功...");
                            return true;
                        }
                    }
                }
            }
            System.out.println("领取营养液失败...");
        } catch (Exception e) {
            System.out.println("领取营养液失败...");
            System.out.println(receiveResult);
            e.printStackTrace();
        }
        return false;
    }

    private static boolean cultureBean(String appCookie, String roundId) {
        String cultureResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=cultureBean&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1561638129816&sign=d6e13aa2c0fa3e6316718c2f83678f60&sv=120";
            String reqBody = "{\"monitor_refer\":\"plant_index\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"" + roundId + "\"}";
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            cultureResult = HttpUtils.postResponse(url, paramList, appHeaders);

            JSONObject cultureJson = JSONObject.parseObject(cultureResult);
            if (null != cultureJson) {
                String code = cultureJson.getString("code");
                if ("0".equals(code)) {
                    JSONObject data = cultureJson.getJSONObject("data");
                    if (null != data) {
                        if (data.containsKey("growth") && data.containsKey("nutrients") && data.containsKey("beanState")) {
                            return true;
                        }
                    }
                }
            }

            System.out.println(cultureResult);
        } catch (Exception e) {
            System.out.println("使用营养液失败");
            System.out.println(cultureResult);
            e.printStackTrace();
        }
        return false;
    }











    private static List<NameValuePair> buildParamList(String body) {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("body", body));
        return paramList;
    }

    private static Header[] builderHeader(String cookie) {

        List<Header> headerList = new ArrayList<>();
        headerList.add(new BasicHeader("Connection", "Keep-Alive"));
        headerList.add(new BasicHeader("Cookie", cookie));
        headerList.add(new BasicHeader("Charset", "UTF-8"));
        headerList.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headerList.add(new BasicHeader("jdc-backup", cookie));
        headerList.add(new BasicHeader("Cache-Control", "no-cache"));
        headerList.add(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"));
        headerList.add(new BasicHeader("Host", "api.m.jd.com"));
        headerList.add(new BasicHeader("User-Agent", "okhttp/3.6.0"));

        Header[] headers = new Header[headerList.size()];
        return headerList.toArray(headers);
    }

}
