package com.course.cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import com.course.utils.JsonUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.course.config.TestConfig.*;

public class LoginTest {

    //    接口测试，首先应该判断是否调用成功，才进行逻辑验证
    private int statusCode;

    //    这里分组loginTrue，并非说登录的异常用例不执行这个，而是说其他需要接口需要获取cookies时，先调用这个组的方法
    @BeforeTest(groups = "loginTrue", description = "测试准备工作,获取HttpClient对象")
    public void beforeTest() {
        addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSER);
        getUserInfoUrl = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
        loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
    }

    @Test(groups = "loginTrue", description = "用户成功登陆接口")
    public void loginTrue() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 1);
        String result = getResult(loginCase);
        Assert.assertEquals(200, statusCode);
        Assert.assertEquals(result, loginCase.getExpected());
    }

    @Test(groups = "loginFalse", description = "用户登录失败")
    public void loginFalse() throws IOException {
        SqlSession session = DatabaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase", 2);
        String result = getResult(loginCase);
        Assert.assertEquals(200, statusCode);
        Assert.assertEquals(result, loginCase.getExpected());
    }

    private String getResult(LoginCase loginCase) throws IOException {
        String result = null;
        HttpPost post = new HttpPost(loginUrl);
        JSONObject parm = new JSONObject();
        parm.put("name", loginCase.getUsername());
        parm.put("password", loginCase.getPassword());
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(parm.toString(), "utf-8");
        post.setEntity(entity);
//        不管是request还是response都是这样设置cookies，牢记就行
        httpClient = HttpClientBuilder.create().setDefaultCookieStore(store).build();
        HttpResponse response = httpClient.execute(post);
        statusCode = response.getStatusLine().getStatusCode();
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        return result;
    }


    @Test
    public void cmpJson() {
        String str1 = "json";
        String str2 = "json";
        JSONObject jsonObject1 = JSONObject.parseObject(str1);
        JSONObject jsonObject2 = JSONObject.parseObject(str2);
        Assert.assertEquals(java.util.Optional.of(JsonUtil.cmpJsonObject(jsonObject1, jsonObject2)), true);


        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        jsonArray1.add("first");
        jsonArray1.add("second");
        jsonArray2.add("first");
        jsonArray2.add("second");
        Assert.assertEquals(JsonUtil.cmpJsonArray(jsonArray1, jsonArray2), java.util.Optional.of(true));
    }

}
