package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnotation {

    /*testng理解：
     * 1.@Test用例编写注解
     * 2.@BeforeMethod、@AfterMethod每一条用例前后执行
     * 3.@BeforeClass、@AfterClass每一个类前后执行，该注解写在测试用例类里
     * 4.@BeforeTest、@AfterTest在类外执行，该注解一般写在配置类中
     * 5.@BeforeSuite、@AfterSuite最外层执行，该注解写在配置类中*/
    @Test
    public void testCase1() {
        System.out.print("testng中Test注解,用例1");
    }

    @Test(enabled = false)
    public void testCase2() {
        System.out.print("testng中Test注解,用例2");
    }

    @Test
    public void testCase3() {
        System.out.println("testng中Test注解,用例3");
    }


    @BeforeMethod
    public void testBeforeMethod() {
        System.out.print("测试BeforeMethod注解");
    }

    @AfterMethod
    public void testAfterMethod() {
        System.out.print("测试AfterMethod注解");
    }

    @BeforeClass
    public void testBeforeClass() {
        System.out.println("测试BeforeClass");
    }

    @AfterClass
    public void testAfterClass() {
        System.out.println("测试AfterClass");
    }

    @BeforeTest
    public void testBeforeTest() {
        System.out.println("测试BeforeTest注解");
    }

    @AfterTest
    public void testAfterTest() {
        System.out.println("测试AfterTest注解");
    }

    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("测试BeforeSuite");
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("测试AfterSuite");
    }
}
