package com.fly.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.model.MyHeader;
import com.fly.utils.CommonUtils;
import com.fly.utils.HeaderUtils;
import com.fly.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class JD {


    public static void main(String[] args) {
        String cookie = "pin=jackdaifei_m;wskey=AAFbT9AfAEAWGgHkSanFbpfjKXgOiumMdr6hsmtr2NT8oMdHMGiyza11FEh5pRhZjDKGmIvaBKfLN7WYk7n3dJOtSXVkvGPJ;whwswswws=zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj/545EHilrLsep8ki5XL/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==;unionwsws={\"devicefinger\":\"eidA2B700114ODY4NjAyMDQ3MTI1NTE3MA==0I7u8gZRJfxuaDVhagH3uQjoCRqDZgV4oZflP8e\\/6uphn9NxD4vvpXaqrAFb0+Oog6wilp42RLPGbnAB\",\"jmafinger\":\"zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj\\/545EHilrLsep8ki5XL\\/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==\"};";
        // signBeanIndex(cookie);
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

    public static void plantBeanIndex(String appCookie) {
        String plantResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=plantBeanIndex&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1561636588607&sign=6684ff6dee506a6a76bc38ab018efedf&sv=100";
            String reqBody = "{\"followType\":\"1\",\"monitor_refer\":\"\",\"monitor_source\":\"plant_app_plant_index\",\"shareUuid\":\"\",\"wxHeadImgUrl\":\"\"}";
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            plantResult = HttpUtils.postMethod(url, paramList, appHeaders);

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
                        Thread.sleep(CommonUtils.randomNum(3000, 14500));
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
                        if (StringUtils.isNotBlank(countDown)) {
                            System.out.println("剩余[" + countDown + "]秒");
                        } else {
                            System.out.println(timeNutrientsRes.getString("promptText"));
                        }
                    }

                    // 使用培养液
                    if (nutrients > 0) {
                        int sleepTime = CommonUtils.randomNum(3500, 13600);
                        System.out.println("随机sleep[" + sleepTime + "]ms");
                        Thread.sleep(sleepTime);
                        boolean useSuccess = cultureBean(appCookie, roundId);
                        if (!useSuccess) {
                            System.out.println("使用营养液失败...");
                        } else {
                            System.out.println("使用营养液成功！！！");
                        }
                    }

                    purchaseRewardTask(appCookie, roundId);
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

            receiveResult = HttpUtils.postMethod(url, paramList, appHeaders);

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
            String url = "http://api.m.jd.com/client.action?functionId=cultureBean&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=dad94a9d1e16905aac6420087a4877cd&st=1562086292549&sign=c66b138816a2e7735f7a049ce00975e6&sv=100";
            String reqBody = "{\"monitor_refer\":\"plant_index\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"" + roundId + "\"}";
            System.out.println(roundId);
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);

            cultureResult = HttpUtils.postMethod(url, paramList, appHeaders);

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

    /**
     * 种豆任务-逛逛会场
     * @param appCookie
     * @param roundId
     */
    private static void purchaseRewardTask(String appCookie, String roundId) {
        String purchaseResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=purchaseRewardTask&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52154&networkType=wifi&wifiBssid=unknown&st=1561796734430&sign=ff5ff58e8639e1d21b7e6c494e78c05b&sv=111";
            String reqBody = "{\"monitor_refer\":\"plant_purchaseRewardTask\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"" + roundId + "\"}";
            Header[] appHeaders = builderHeader(appCookie);
            List<NameValuePair> paramList = buildParamList(reqBody);
            purchaseResult = HttpUtils.postMethod(url, paramList, appHeaders);
            if (null != purchaseResult) {
                // {"code":"0","data":{"nutrState":"1"}}
                System.out.println(purchaseResult);
            }
        } catch (Exception e) {
            System.out.println(purchaseResult);
            e.printStackTrace();
        }
    }

    private static void productTaskList(String appCookie) throws Exception {
        String url = "http://api.m.jd.com/client.action?functionId=productTaskList&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562148264318&sign=f3a06335a6fcedf45b18c295a6a1de16&sv=101";
        String reqBody = "{\"monitor_refer\":\"plant_productTaskList\",\"monitor_source\":\"plant_app_plant_index\"}";
        Header[] appHeaders = builderHeader(appCookie);
        List<NameValuePair> paramList = buildParamList(reqBody);

        JSONObject productTaskJson = HttpUtils.postMethodJson(url, paramList, appHeaders);

        if (null != productTaskJson) {
            if ("0".equals(productTaskJson.getString("code"))) {
                JSONObject data = productTaskJson.getJSONObject("data");
                int gainNutrients = data.getIntValue("gainNutrients");
                int maxNutrients = data.getIntValue("maxNutrients");
                if (gainNutrients < maxNutrients) {
                    JSONArray productInfoList = data.getJSONArray("productInfoList");
                    for (int i=0;i<productInfoList.size();i++) {
                        JSONArray productInfoArray = productInfoList.getJSONArray(i);

                        // 获取随机下标(0, 1)
                        int index = CommonUtils.randomModNum(2);
                        JSONObject productInfo = productInfoArray.getJSONObject(index);
                        if (!"2".equals(productInfo.getString("taskState"))) {
                            continue;
                        }

                        productNutrientsTask(appCookie, productInfo.getString("productTaskId"), productInfo.getString("skuId"));

                        int sleepNum = CommonUtils.randomNum(8000, 23042);
                        Thread.sleep(sleepNum);
                    }
                }
            }
        }

    }

    private static void productNutrientsTask(String appCookie, String productTaskId, String skuId) throws Exception {
        String url = "http://api.m.jd.com/client.action?functionId=productNutrientsTask&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=unknown&st=1562152334117&sign=082fce91f9acfcf6d4db925cbd4e15ee&sv=100";
        String reqBody = "{\"monitor_refer\":\"plant_productNutrientsTask\",\"monitor_source\":\"plant_app_plant_index\",\"productTaskId\":\"" + productTaskId + "\",\"skuId\":\"" + skuId + "\"}";
        Header[] appHeaders = builderHeader(appCookie);
        List<NameValuePair> paramList = buildParamList(reqBody);

        System.out.println("productNutrientsTask---->>>>" + reqBody);
        JSONObject productTaskJson = HttpUtils.postMethodJson(url, paramList, appHeaders);

        System.out.println("productNutrientsTask---->>>>" + productTaskJson);

        int sleepNum = CommonUtils.randomNum(8203, 30201);
        Thread.sleep(sleepNum);

        cancelFavorite(appCookie, skuId);

    }

    private static void cancelFavorite(String appCookie, String skuId) throws Exception {
        String url = "http://api.m.jd.com/client.action?functionId=cancelFavorite&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562152950466&sign=5504b62221c907a582671ad1fb46e1bb&sv=110";
        String reqBody = "{\"wareId\":\"" + skuId + "\"}";
        Header[] appHeaders = builderHeader(appCookie);
        List<NameValuePair> paramList = buildParamList(reqBody);

        JSONObject cancelJson = HttpUtils.postMethodJson(url, paramList, appHeaders);
        System.out.println("cancelFavorite---->>>>" + cancelJson);
    }










    private static List<NameValuePair> buildParamList(String body) {
        List<NameValuePair> paramList = new ArrayList<>();
        paramList.add(new BasicNameValuePair("body", body));
        return paramList;
    }

    private static Header[] builderHeader(String cookie) {
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

}
