package com.course.testng.paramter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class DataProviderTest {

    @Test(dataProvider = "data")
    public void testDataProvider(String name, String age, int param3, String param4) {
        System.out.println(name + ";" + age + ";param3=" + param3 + ";param4=" + param4);
    }

    @DataProvider(name = "data")
    public Object[][] providerData1() {
        Object[][] object = new Object[][]{
                {"zhangsan", "18", 52, "param4"},
                {"lisi", "56", 63, "param4"}
        };
        return object;
    }

    @Test(dataProvider = "methodData")
    public void testMethodDataProvider1(String name, int age) {
        System.out.println("test1111:" + name + ";" + age);
    }

    @Test(dataProvider = "methodData")
    public void testMethodDataProvider2(String name, int age) {
        System.out.println("test222222" + name + ";" + age);
    }

    @DataProvider(name = "methodData")
    public Object[][] providerData2(Method method) {
        Object[][] object = null;
        if (method.getName().equals("testMethodDataProvider1")) {
            object = new Object[][]{
                    {"zhangsan", 52},
                    {"lisi", 23}
            };
        } else if (method.getName().equals("testMethodDataProvider2")) {
            object = new Object[][]{
                    {"zhangsan", 52},
                    {"lisi", 23},
                    {"wangwu", 23}
            };
        }
        return object;
    }
}