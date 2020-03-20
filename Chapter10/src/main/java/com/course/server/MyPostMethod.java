package com.course.server;

import com.course.bean.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "/")
@RestController
@RequestMapping(value = "/v1")
public class MyPostMethod {

    private static Cookie cookie;

    @ApiOperation(value = "登录接口，返回cookies信息", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response,
                        @RequestParam(value = "userName", required = true) String userName,
                        @RequestParam(value = "password", required = true) String password) {

        if (userName.isEmpty() || password.isEmpty()) {
            return "请输入账号密码";
        } else if (userName.equals("yupengcheng") && password.equals("123456")) {
            cookie = new Cookie("status", "true");
            response.addCookie(cookie);
            return "登录成功";
        } else {
            return "请检查账号密码";
        }
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "带有cookies访问的post请求", httpMethod = "POST")
    public String getUserList(HttpServletRequest request, @RequestBody User user) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("status") && cookie.getValue().equals("true")) {
                if (user.getUser().equals("yupengcheng")) {
                    user.setUserName("Chinese");
                    user.setPassword("*********");
                    user.setUser("yupengcheng");
                    user.setAge(30);
                    user.setSex("boy");
                    return user.toString();
                } else {
                    return "入参有问题，请检查";
                }
            }
        }
        return "未登录，cookies校验失败";
    }
}
