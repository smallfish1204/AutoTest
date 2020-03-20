package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {

    @Test
    @Parameters({"name", "age"})
    public void paramterWithXml(String name, String age) {
        /*这种参数化，只能配置一个参数，可见一般配置系统变量，不作为用例变量配置*/
        System.out.println(name + ";" + age);
    }
}
