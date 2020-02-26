package com.course.testng;

import org.testng.annotations.Test;

public class DependsOnMethods {

    /*dependsOnMethods、dependsOnGroups
    依赖的意思是，必须依赖用例执行成功才会执行该用例
    */

    @Test(expectedExceptions = RuntimeException.class)
    public void test1() {
        System.out.println("test1 run");
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2() {
        System.out.println("test2 run");
    }
}
