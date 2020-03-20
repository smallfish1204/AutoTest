package com.course.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/")
public class MyGetMethod {

    @RequestMapping(value = "/get/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "这是一个获取cookies的接口", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        /*HttpServerletRequest 装请求信息的类
         * HttpServerletResponse 装响应信息的类*/
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你获得cookies信息接口返回成功";
    }

    @RequestMapping(value = "/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "这是一个带有cookies信息才能调用的接口", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带cookies信息才能调用成功";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("yupengcheng") && cookie.getValue().equals("me")) {
                return "cookies信息验证通过";
            }
        }
        return "你必须携带cookies信息才能调用成功";
    }

    /*模拟带有参数的get请求
     * 格式：?name=value&name=value*/
    @ApiOperation(value = "这是带有参数的get接口", httpMethod = "GET")
    @RequestMapping(value = "/get/with/params", method = RequestMethod.GET)
    public Map<String, Integer> getList(@RequestParam String name,
                                        @RequestParam Integer age) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 280);
        myList.put("衣服", 120);
        myList.put("内裤", 35);
        return myList;
    }

    /*特别注意：入参注释和上面那个不一样*/
    @RequestMapping(value = "/get/params/{name}/{age}")
    @ApiOperation(value = "这也是带有参数的get接口，但调试有问题", httpMethod = "GET")
    public Map<String, Integer> myGetList(@PathVariable String name,
                                          @PathVariable Integer age) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 280);
        myList.put("衣服", 120);
        myList.put("内裤", 35);
        return myList;
    }

}
