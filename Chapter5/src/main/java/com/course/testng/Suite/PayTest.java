package com.course.testng.Suite;

import org.testng.annotations.*;

public class PayTest {

    @Test
    public void testPayCase() {
        System.out.println("支付宝支付成功");
    }

    @BeforeMethod
    public void testPayBeforeMethod() {
        System.out.println("PayBeforeMethod测试");
    }

    @AfterMethod
    public void testPayAfterMethod() {
        System.out.println("PayAfterMethod测试");
    }

    @BeforeClass
    public void testPayBeforeClass() {
        System.out.println("PayBeforeClass测试");
    }

    @AfterClass
    public void testPayAfterClass() {
        System.out.println("PayAfterClass测试");
    }
}
