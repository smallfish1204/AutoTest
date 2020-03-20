package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForGet {

    private String uri;
    private ResourceBundle bundle;
    private CookieStore cookieStore = new BasicCookieStore();
    private CloseableHttpClient client;

    @BeforeTest
    public void beforTest() {
        /*读取配置文件*/
//        根目录为对应模块下的resources
        this.bundle = ResourceBundle.getBundle("application");
        this.uri = bundle.getString("test.uri");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
        String myuri = this.uri + this.bundle.getString("test.get.cookies");
        HttpGet get = new HttpGet(myuri);
//        客户端的创建需要特意记一下，不是new
        this.client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = client.execute(get);
//        最好设置一下字符集，以免发生错误
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        List<Cookie> cookies = this.cookieStore.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println(name + "=" + value);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void withCookies() throws IOException {
        String result;
        String myuri = this.uri + this.bundle.getString("test.with.cookies");
        HttpGet get = new HttpGet(myuri);
        this.client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        if (statusCode == 200) {
            System.out.println(result);
        }
    }

    @AfterTest
    public void afterTest() throws IOException {
        this.client.close();
    }
}
