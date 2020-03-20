package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MuitiThreadOnAnnotion {

    /*invocationCount：调用次数
     * threadPoolSize：执行的线程数*/

    @Test(invocationCount = 10, threadPoolSize = 3)
    public void test() {
        System.out.println(1);
        System.out.println("Thread Id:" + Thread.currentThread().getId());
    }
}
