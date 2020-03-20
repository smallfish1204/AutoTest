package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j
@RestController
@Api(value = "v1")
@RequestMapping("v1")
public class Demo {

//    首先获取一个执行sql语句的对象

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/get/uesr/count", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数", httpMethod = "GET")
    public int getUserCount() {
        return template.selectOne("getUserCount");
    }

    @ApiOperation(value = "查询用户信息", httpMethod = "GET")
    @RequestMapping(value = "/get/one/user", method = RequestMethod.GET)
    public User getOneUser(@RequestParam Integer id) {
        return template.selectOne("getOneUser", id);
    }

    @ApiOperation(value = "查询用户列表",httpMethod = "GET")
    @RequestMapping(value = "get/user/list",method = RequestMethod.GET)
    public List<User> getUserList(){
        return template.selectList("getUserList");
    }

    @ApiOperation(value = "新增用户",httpMethod = "POST")
    @RequestMapping(value = "/add/user",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        return template.insert("addUser", user);
    }

}
