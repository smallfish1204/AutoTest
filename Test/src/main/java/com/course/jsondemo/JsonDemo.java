package com.course.jsondemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class JsonDemo {

    public static void main(String[] args) {

        String param = "[{\"third\":{\"first\":\"java\",\"second\":\"python\"},\"first\":\"java\",\"second\":\"python\"}]";

        JSONArray akeArr = JSONArray.parseArray(param);

        JSONArray keywordArray = new JSONArray();

        for (int i = 0; i < akeArr.size(); i++) {

            JSONObject jsonObj = akeArr.getJSONObject(i);
            System.out.println(i + jsonObj.toString());
            JSONObject keywordJO = new JSONObject();

            for (Map.Entry<String, Object> entry : jsonObj.entrySet()) {

                keywordJO.put("word", entry.getKey());

                keywordJO.put("weight", entry.getValue());

            }

            keywordArray.add(keywordJO);

        }

        System.out.println(keywordArray);
    }
}
