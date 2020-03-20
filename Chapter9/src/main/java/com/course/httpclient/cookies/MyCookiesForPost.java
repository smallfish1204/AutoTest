package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private String uri;
    private ResourceBundle bundle;
    private CloseableHttpClient client;
    private CookieStore cookieStore = new BasicCookieStore();

    @BeforeTest
    public void beforeTest() {
        this.bundle = ResourceBundle.getBundle("application");
        this.uri = bundle.getString("test.uri");
    }

    @Test
    public void getCookies() throws IOException, URISyntaxException {
        String result;
        String myuri = this.uri + this.bundle.getString("test.get.cookies.params");
//        get请求添加参数的方式
        ArrayList<NameValuePair> queryParams = new ArrayList<NameValuePair>();
        queryParams.add(new BasicNameValuePair("name", "yaolianchun"));
        queryParams.add(new BasicNameValuePair("alias", "lover"));
//        get请求拼接UR
        URI myuriParams = new URIBuilder(myuri).setParameters(queryParams).build();
        System.out.println(myuriParams.toString());
        HttpGet get = new HttpGet(myuriParams);
//        客户端的创建需要特意记一下，不是new
        this.client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = this.client.execute(get);
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
    public void httpclientForPost() throws IOException {
        String myuri = this.uri + bundle.getString("test.with.params");
        System.out.println(myuri);
        HttpPost post = new HttpPost(myuri);
        //设置请求头
        post.addHeader("Content-Type", "application/json");
        post.setHeader("charset", "utf-8");
        //设置参数
        JSONObject json = new JSONObject();
        json.put("name", "yupengcheng");
        json.put("lover", "yaolianchun");
        json.put("age", 18);
//        将参数信息添加到方法中
        StringEntity entity = new StringEntity(json.toString(), "utf-8");
        post.setEntity(entity);
        //设置cookies
        this.client = HttpClientBuilder.create().setDefaultCookieStore(this.cookieStore).build();
        HttpResponse response = this.client.execute(post);
        int status = response.getStatusLine().getStatusCode();
        System.out.println(status);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);

//        结果判断
        JSONObject resultJson = new JSONObject(result);
        int resultCode = resultJson.getInt("resultCode");
        Assert.assertEquals(resultCode, 1, "断言失败的输出内容");

    }

    @AfterTest
    public void afterTest() throws IOException {
        client.close();
    }
}
