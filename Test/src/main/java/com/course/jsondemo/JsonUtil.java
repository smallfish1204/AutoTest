package com.course.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    /**
     * 判断实际结果和预期结果是否一致，在预期结果中可以去除不需要验证的key
     *
     * @param actualJson
     * @param exceptedJson
     * @return
     */
    public static Boolean cmpJsonObject(JSONObject actualJson, JSONObject exceptedJson) {
        List<String> reultList = new LinkedList();
        for (Map.Entry<String, Object> actualEntry : actualJson.entrySet()) {
            for (Map.Entry<String, Object> exceptedEntry : exceptedJson.entrySet()) {
                if (actualEntry.getKey().equals(exceptedEntry.getKey())) {
                    if (!actualEntry.getValue().equals(exceptedEntry.getValue())) {
                        reultList.add(actualEntry.getKey());
                    }
                }
            }
        }
        if (reultList.size() > 0) {
            for (String s : reultList) {
                System.out.println("不一致的key为" + s);
                System.out.println(actualJson.getString(s));
                System.out.println(exceptedJson.getString(s));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * @param actualJsonArray
     * @param exceptedJsonArray
     * @return
     */
    public static Boolean cmpJsonArray(JSONArray actualJsonArray, JSONArray exceptedJsonArray) {
        List<String> reultList = new LinkedList();
        if (actualJsonArray.size() == exceptedJsonArray.size()) {
            for (int i = 0; i < actualJsonArray.size(); i++) {
                if (verifyJsonObject(actualJsonArray.getString(i)) && verifyJsonObject(exceptedJsonArray.getString(i))) {
                    if (!cmpJsonObject(actualJsonArray.getJSONObject(i), exceptedJsonArray.getJSONObject(i))) {
                        reultList.add(String.valueOf(i));
                    }
                } else {
                    if (!actualJsonArray.get(i).equals(exceptedJsonArray.get(i))) {
                        reultList.add(String.valueOf(i));
                    }
                }
            }
        }
        if (reultList.size() > 0) {
            for (String s : reultList) {
                System.out.println("不一致的index为" + s);
                System.out.println(actualJsonArray.getString(Integer.valueOf(s)));
                System.out.println(exceptedJsonArray.getString(Integer.valueOf(s)));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验是否是json串
     *
     * @param jsonString
     * @return Boolean
     */
    private static Boolean verifyJson(String jsonString) {
        try {
            JSON.parse(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否是JSONObject类型的json
     *
     * @param jsonString
     * @return Boolean
     */
    public static Boolean verifyJsonObject(String jsonString) {
        if (!verifyJson(jsonString)) {
            return false;
        }
        try {
            JSONObject.parseObject(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否是JSONArray类型的json
     *
     * @param jsonString
     * @return Boolean
     */
    public static Boolean verifyJsonArray(String jsonString) {
        if (!verifyJson(jsonString)) {
            return false;
        }
        try {
            JSONArray.parseArray(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String str1 = "{\"title\": \"新增的任务的表单信息\", \"type\": \"object\", \"properties\": {\"finishTime\": {\"type\": \"number\", \"title\": \"任务结束时间例如：1450708950086\"}, \"contactEmail\": {\"type\": \"string\", \"title\": \"联系人邮箱\"}, \"sexType\": {\"type\": \"string\", \"title\": \"招聘性别要求,FEMALE:女，MALE：男，UNKWON：男女不限\"}, \"enrollEndTime\": {\"type\": \"number\", \"title\": \"报名截止时间例如：1450708950086\"}, \"description\": {\"type\": \"string\", \"title\": \"备注\"}, \"weekday\": {\"type\": \"string\", \"title\": \"工作日期\"}, \"title\": {\"type\": \"string\", \"title\": \"任务标题\"}, \"startTime\": {\"type\": \"number\", \"title\": \"任务开始时间,例如：1450708950086\"}, \"contactTelephone\": {\"type\": \"string\", \"title\": \"联系人电话\"}, \"timeDescription\": {\"type\": \"string\", \"title\": \"工作时间描述\"}, \"paymentType\": {\"type\": \"string\", \"title\": \"支付类型 0：线上支付，1：线下支付\"}, \"addressList\": {\"items\": {\"properties\": {\"latitude\": {\"type\": \"string\", \"title\": \"纬度\"}, \"cityName\": {\"type\": \"string\", \"title\": \"城市名称\"}, \"areaName\": {\"type\": \"string\", \"title\": \"区域名称\"}, \"longitude\": {\"type\": \"string\", \"title\": \"经度\"}, \"address\": {\"type\": \"string\", \"title\": \"t工作地点\"}}, \"type\": \"object\", \"description\": \"任务地址\"}, \"type\": \"array\", \"title\": \"地址列表\"}, \"balanceUnit\": {\"type\": \"string\", \"title\": \"兼职金额单位；例如：元/小时，元/天等\"}, \"contactName\": {\"type\": \"string\", \"title\": \"联系人姓名\"}, \"content\": {\"type\": \"string\", \"title\": \"工作内容\"}, \"balance\": {\"type\": \"number\", \"title\": \"兼职金额\"}, \"headcount\": {\"type\": \"integer\", \"title\": \"招聘人数\"}, \"typeDesc\": {\"type\": \"string\", \"title\": \"任务类型描述: 只有在任务类型为其他时有效\"}, \"type\": {\"type\": \"string\", \"title\": \"任务类型；例如：传单派发，服务员等\"}, \"balanceType\": {\"type\": \"integer\", \"title\": \"结算类型；例如：完工结，日结等\"}}}";
        String str2 = "{\"title\": \"新增的任务表单信息\", \"properties\": {\"finishTime\": {\"title\": " +
                "\"任务结束时间例如：1450708950086\",\"type\": \"number\"}, \"contactEmail\": {\"type\": \"string\", \"title\": \"联系人邮箱\"}, \"sexType\": {\"type\": \"string\", \"title\": \"招聘性别要求,FEMALE:女，MALE：男，UNKWON：男女不限\"}, \"enrollEndTime\": {\"type\": \"number\", \"title\": \"报名截止时间例如：1450708950086\"}, \"description\": {\"type\": \"string\", \"title\": \"备注\"}, \"weekday\": {\"type\": \"string\", \"title\": \"工作日期\"}, \"title\": {\"type\": \"string\", \"title\": \"任务标题\"}, \"startTime\": {\"type\": \"number\", \"title\": \"任务开始时间,例如：1450708950086\"}, \"contactTelephone\": {\"type\": \"string\", \"title\": \"联系人电话\"}, \"timeDescription\": {\"type\": \"string\", \"title\": \"工作时间描述\"}, \"paymentType\": {\"type\": \"string\", \"title\": \"支付类型 0：线上支付，1：线下支付\"}, \"addressList\": {\"items\": {\"properties\": {\"latitude\": {\"type\": \"string\", \"title\": \"纬度\"}, \"cityName\": {\"type\": \"string\", \"title\": \"城市名称\"}, \"areaName\": {\"type\": \"string\", \"title\": \"区域名称\"}, \"longitude\": {\"type\": \"string\", \"title\": \"经度\"}, \"address\": {\"type\": \"string\", \"title\": \"t工作地点\"}}, \"type\": \"object\", \"description\": \"任务地址\"}, \"type\": \"array\", \"title\": \"地址列表\"}, \"balanceUnit\": {\"type\": \"string\", \"title\": \"兼职金额单位；例如：元/小时，元/天等\"}, \"contactName\": {\"type\": \"string\", \"title\": \"联系人姓名\"}, \"content\": {\"type\": \"string\", \"title\": \"工作内容\"}, \"balance\": {\"type\": \"number\", \"title\": \"兼职金额\"}, \"headcount\": {\"type\": \"integer\", \"title\": \"招聘人数\"}, \"typeDesc\": {\"type\": \"string\", \"title\": \"任务类型描述: 只有在任务类型为其他时有效\"}, \"type\": {\"type\": \"string\", \"title\": \"任务类型；例如：传单派发，服务员等\"}, \"balanceType\": {\"type\": \"integer\", \"title\": \"结算类型；例如：完工结，日结等\"}}}";
        JSONObject jsonObject1 = JSONObject.parseObject(str1);
        JSONObject jsonObject2 = JSONObject.parseObject(str2);
        System.out.println(cmpJsonObject(jsonObject1, jsonObject2));
        System.out.println(verifyJsonArray(str1));


        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        jsonArray1.add("first");
        jsonArray1.add("second");
        jsonArray2.add("first");
        jsonArray2.add("second");
        jsonArray1.add(jsonObject1);
        jsonArray2.add(jsonObject2);
        System.out.println(cmpJsonArray(jsonArray1, jsonArray2));
    }

}
