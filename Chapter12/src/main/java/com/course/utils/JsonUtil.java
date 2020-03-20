package com.course.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Log4j
public class JsonUtil {

    /**
     * 判断实际结果和预期结果是否一致，在预期结果中可以去除不需要验证的key
     *
     * @param actualJson
     * @param exceptedJson
     * @return
     */
    public static Boolean cmpJsonObject(JSONObject actualJson, JSONObject exceptedJson) {
        List<String> reultList = new LinkedList<String>();
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
            log.info("共有--->" + reultList.size() + "<---对键值对不匹配");
            for (String s : reultList) {
                log.info(reultList.indexOf(s) + ":");
                log.info("实际值：" + actualJson.getString(s));
                log.info("预期值：" + exceptedJson.getString(s));
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
        List<String> reultList = new LinkedList<String>();
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
            log.info("共有--->" + reultList.size() + "<---对键值对不匹配");
            for (String s : reultList) {
                log.info(reultList.indexOf(s) + ":");
                log.info("实际值：" + actualJsonArray.getString(Integer.valueOf(s)));
                log.info("预期值：" + exceptedJsonArray.getString(Integer.valueOf(s)));
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
}
