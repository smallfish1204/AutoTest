package com.course.httpclient.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClient {

    @Test
    public void test1() {
        String result;
        HttpGet get = new HttpGet("http://www.baidu.com");
//        过时的方法，采用可关闭的方法
//        HttpClient client = new DefaultHttpClient();
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpResponse response = client.execute(get);
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
