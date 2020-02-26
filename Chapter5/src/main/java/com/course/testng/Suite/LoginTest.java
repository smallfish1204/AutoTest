package com.course.testng.Suite;

import org.testng.annotations.*;

public class LoginTest {

    @Test
    public void testLoginCase() {
        System.out.println("淘宝登录成功");
    }

    @BeforeMethod
    public void testLoginBeforeMethod() {
        System.out.println("LoginBeforeMethod测试");
    }

    @AfterMethod
    public void testLoginAfterMethod() {
        System.out.println("LoginAfterMethod测试");
    }

    @BeforeClass
    public void testLoginBeforeClass() {
        System.out.println("LoginBeforeClass测试");
    }

    @AfterClass
    public void testLoginAfterClass() {
        System.out.println("LoginAfterClass测试");
    }
}
