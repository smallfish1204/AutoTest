package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {

    /*注解中的异常，是预期抓取的异常
     * runTimeExceptionFailed该用例是，预期获取异常，但没获取到，所以用例执行失败
     * runTimeExceptionSuccess该用例则是，预期获取异常，获取到了，所以用例执行成功
     * */

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionFailed() {
        System.out.println("这是一个失败的异常测试");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void runTimeExceptionSuccess() {
        System.out.println("这是一个成功的异常测试");
        throw new RuntimeException();
    }
}
