package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value = "/v1")
@RequestMapping(value = "/v1")
public class UserManager {

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登录接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody User user) {
//        走数据库校验账号密码
        int selectCount = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        if (selectCount == 1) {
            log.info("登录的用户是：" + user.getUsername());
            return true;
        } else {
            return false;
        }
    }

    @ApiOperation(value = "添加用户信息", httpMethod = "POST")
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request, @RequestBody User user) {
        if (verifyCookies(request)) {
//            判断必传字段是否为空
            if ((user.getUsername().equals("") && user.getUsername() == null) || (user.getPassword().equals("") && user.getPassword() == null)) {
                log.info("检查用户或密码是否传值");
                return false;
            }
//            创建数据的时候，时间应更新系统时间
            if (user.getCreate_time().equals("") && user.getCreate_time() == null) {
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date.getTime());
                user.setCreate_time(timestamp);
                user.setUpdate_time(timestamp);
            }
            int insertCount = template.insert("addUser", user);
            if (insertCount > 0) {
                log.info("添加用户的数量为：" + insertCount);
                return true;
            } else {
                log.info("添加用户失败");
            }
        } else {
            log.info("cookies验证失败");
        }
        return false;
    }

    @RequestMapping(value = "/get/user/list", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户（列表）信息接口", httpMethod = "POST")
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        if (verifyCookies(request)) {
            List<User> users = template.selectList("getUserInfo", user);
            log.info("getUserList获取用户数量：" + users.size());
            return users;
        } else {
            log.info("cookies验证失败");
            return null;
        }
    }

    @RequestMapping(value = "/update/user/info", method = RequestMethod.POST)
    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST")
    public Boolean updateUser(HttpServletRequest request, @RequestBody User user) {
        if (verifyCookies(request)) {
            int updateCount = template.update("updateUserInfo", user);
            if (updateCount > 0) {
                log.info("修改用户的数量为：" + updateCount);
                return true;
            } else {
                log.info("修改失败");
            }
        } else {
            log.info("cookies验证失败");
        }
        return false;
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空，请传入cookies");
            return false;
        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                    log.info("cookies验证通过");
                    return true;
                } else {
                    log.info("cookies传入错误");
                    return false;
                }
            }
        }
        return false;
    }
}
