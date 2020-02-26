package com.course.testng.Suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class SuiteConfig {

    @BeforeSuite
    public void testBeforeSuite() {
        System.out.println("测试BeforeSuite");
    }

    @AfterSuite
    public void testAfterSuite() {
        System.out.println("测试AfterSuite");
    }

    @BeforeTest
    public void testBeforeTest() {
        System.out.println("测试BeforeTest");
    }

    @AfterTest
    public void testAfterTest() {
        System.out.println("测试AfterTest");
    }
}
