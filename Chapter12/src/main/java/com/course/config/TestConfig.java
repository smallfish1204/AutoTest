package com.course.config;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.CloseableHttpClient;

public class TestConfig {
    public static String loginUrl;
    public static String updateUserInfoUrl;
    public static String getUserListUrl;
    public static String getUserInfoUrl;
    public static String addUserUrl;

    //    用来存储cookies信息
    public static CookieStore store;
    //    声明http客户端
    public static CloseableHttpClient httpClient;
}
