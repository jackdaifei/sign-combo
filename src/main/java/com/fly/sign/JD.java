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


    public static void main(String[] args) throws Exception {
        String cookie = "pin=jackdaifei_m;wskey=AAFbT9AfAEAWGgHkSanFbpfjKXgOiumMdr6hsmtr2NT8oMdHMGiyza11FEh5pRhZjDKGmIvaBKfLN7WYk7n3dJOtSXVkvGPJ;whwswswws=zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj/545EHilrLsep8ki5XL/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==;unionwsws={\"devicefinger\":\"eidA2B700114ODY4NjAyMDQ3MTI1NTE3MA==0I7u8gZRJfxuaDVhagH3uQjoCRqDZgV4oZflP8e\\/6uphn9NxD4vvpXaqrAFb0+Oog6wilp42RLPGbnAB\",\"jmafinger\":\"zfKxNSJYpkuxQ2l5Cz9M6SAKwbIQBt9YIF6Fj\\/545EHilrLsep8ki5XL\\/aLuKoCgVUaOazA9eTDSkefFN3XT7Xg==\"};";

        // while (true) {
        //     plantBeanIndex(cookie);
        //     int start = 3600 + CommonUtils.randomNum(80, 200);
        //     int end = 7600 + CommonUtils.randomNum(99, 300);
        //     int sleepNum = CommonUtils.randomNum(1000*start, end*1000);
        //     System.out.println("循环sleep" + sleepNum/1000/60 + "m");
        //     Thread.sleep(sleepNum);
        // }

        // productTaskList(cookie);
    }


    public static void plantBeanIndex(String appCookie) {
        String plantResult = "";
        try {
            String url = "http://api.m.jd.com/client.action?functionId=plantBeanIndex&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=9a26334eabcc7c20902c6619627c1801&st=1562549477857&sign=d556aa0ac7ee7ff498a2d1615bafed45&sv=121";
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
                        int sleepNum = CommonUtils.randomNum(3000, 14500);
                        System.out.println("领取前随机sleep " + sleepNum + "ms");
                        // 随机停几秒
                        Thread.sleep(sleepNum);
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
                        System.out.println("随机sleep " + sleepTime + "ms");
                        Thread.sleep(sleepTime);
                        boolean useSuccess = cultureBean(appCookie, roundId);
                        if (!useSuccess) {
                            System.out.println("使用营养液失败...");
                        } else {
                            System.out.println("使用营养液成功！！！");
                        }
                    }

                    // purchaseRewardTask(appCookie, roundId);
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
            String url = "http://api.m.jd.com/client.action?functionId=receiveNutrients&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=9a26334eabcc7c20902c6619627c1801&st=1562549520205&sign=e5cc2a2000078b239446a195fe212627&sv=100";
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
            String url = "http://api.m.jd.com/client.action?functionId=cultureBean&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=9a26334eabcc7c20902c6619627c1801&st=1562549550337&sign=ff9406f17bd5fc83f1fa58df8229e0a3&sv=120";
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
            String url = "http://api.m.jd.com/client.action?functionId=purchaseRewardTask&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=9a26334eabcc7c20902c6619627c1801&st=1562549642657&sign=60547fc73f5b3ef84b42ea34d614bb4c&sv=120";
            String reqBody = "{\"monitor_refer\":\"plant_purchaseRewardTask\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"" + roundId + "\"}";
            // String reqBody = "{\"monitor_refer\":\"plant_purchaseRewardTask\",\"monitor_source\":\"plant_app_plant_index\",\"roundId\":\"ru72anf4ao52moqbns6eertieu\"}";

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
        String url = "http://api.m.jd.com/client.action?functionId=productTaskList&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562203960843&sign=f405e685792fcbf766c5fa0dae4fd506&sv=102";
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
                        boolean chosen = false;
                        for (int m=0;m<2;m++) {
                            JSONObject tmpProductInfo = productInfoArray.getJSONObject(m);
                            if (!"2".equals(tmpProductInfo.getString("taskState"))) {
                                chosen = true;
                            }
                        }
                        if (chosen) {
                            continue;
                        }
                        JSONObject productInfo = productInfoArray.getJSONObject(index);
                        System.out.println(productInfo);
                        String productTaskId = productInfo.getString("productTaskId");
                        String skuId = productInfo.getString("skuId");
                        System.out.println(productTaskId + "," + skuId);

                        productNutrientsTask(appCookie, productTaskId, skuId);

                        int sleepNum = CommonUtils.randomNum(8000, 23042);
                        Thread.sleep(sleepNum);
                    }
                }
            }
        }

    }

    private static void productNutrientsTask(String appCookie, String productTaskId, String skuId) throws Exception {
        String url = "http://api.m.jd.com/client.action?functionId=productNutrientsTask&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562204192941&sign=ae9a392b8f5e87c8f8d32765dce1572a&sv=100";
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
        String url = "http://api.m.jd.com/client.action?functionId=cancelFavorite&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562204386777&sign=947bb5662801491e8c163f4e8a1ea6ed&sv=121";
        // String url = "http://api.m.jd.com/client.action?functionId=cancelFavorite&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562152950466&sign=5504b62221c907a582671ad1fb46e1bb&sv=110";
        // String reqBody = "{\"wareId\":\"" + skuId + "\"}";
        String reqBody = "{\"wareId\":\"" + skuId + "\"}";
        Header[] appHeaders = builderHeader(appCookie);
        List<NameValuePair> paramList = buildParamList(reqBody);

        JSONObject cancelJson = HttpUtils.postMethodJson(url, paramList, appHeaders);
        System.out.println("cancelFavorite---->>>>" + cancelJson);
    }

    private static void plantFriendList(String appCookie) {
        String url = "http://api.m.jd.com/client.action?functionId=plantFriendList&clientVersion=8.1.2&build=67636&client=android&d_brand=vivo&d_model=vivoZ1&osVersion=9&screen=2201*1080&partner=vivo&androidId=c151521cf7c1430f&installtionId=73e5ce8937244403ae1e93dc8d3ecf69&sdkVersion=28&lang=zh_CN&uuid=868602047125517-20f77c733fa1&area=22_1930_50949_52153&networkType=wifi&wifiBssid=bde5dcf42ebb1788f7cbf43dee7f84bb&st=1562205139734&sign=10478a2be4473f78e5db8b4c8c211590&sv=121";
        String reqBody = "{\"monitor_refer\":\"plantFriendList\",\"monitor_source\":\"plant_app_plant_index\",\"pageNum\":\"1\"}";
        Header[] appHeaders = builderHeader(appCookie);
        List<NameValuePair> paramList = buildParamList(reqBody);

        JSONObject plantFriendJson = HttpUtils.postMethodJson(url, paramList, appHeaders);
        System.out.println("plantFriendList---->>>>" + plantFriendJson);
    }

    private static void a() {
        String url = "https://api.m.jd.com/client.action?functionId=lotteryDraw&body=%7B%22actId%22%3A%22jgpqtzjhvaoym%22%2C%22appSource%22%3A%22jdhome%22%2C%22lotteryCode%22%3A%22mwsevpvqu3t57je23kq7pva3wb6e2sbuc4ihzru63p5pso7sqeq5fz65ajnlm2llhiawzpccizuck%22%7D&appid=ld&client=android&clientVersion=8.1.2&networkType=wifi&osVersion=9&uuid=868602047125517-20f77c733fa1&jsonp=jsonp_1562205320367_89675";

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
