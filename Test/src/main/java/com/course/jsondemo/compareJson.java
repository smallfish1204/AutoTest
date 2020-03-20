package com.course.jsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class compareJson {

    private static Boolean equalsJson() {
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONArray jsonArray1 = new JSONArray();

        jsonObject1.put("first", "java");
        jsonObject1.put("second", "python");

        jsonObject2.put("first", "java");
        jsonObject2.put("second", "python");
        jsonObject1.put("third", jsonObject2);
        jsonArray1.add(0, jsonObject1);
        System.out.println("jsonObject1：" + jsonObject1);
        System.out.println("jsonObject2：" + jsonObject2);
        System.out.println("jsonArray1：" + jsonArray1);
        String str1 = jsonArray1.toString();
        try {
            Object o = JSON.parse(str1);
            System.out.println(o);
            return true;
        } catch (Exception e) {
            return false;
//            return jsonObject1.equals(jsonObject2);
        }
    }


    public static void main(String[] args) {
        System.out.println("hello world!");
        System.out.println(equalsJson());
    }
}
