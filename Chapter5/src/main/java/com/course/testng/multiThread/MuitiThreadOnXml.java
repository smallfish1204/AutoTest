package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MuitiThreadOnXml {

    @Test
    public void test1() throws InterruptedException {
        Thread.sleep(2000);
        System.out.println("test11111-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test2() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("test22222-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test3() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("test33333-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test4() throws InterruptedException {
        Thread.sleep(230);
        System.out.println("test44444-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test5() {
        System.out.println("test55555-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test6() {
        System.out.println("test66666-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test7() {
        System.out.println("test77777-Thread Id:" + Thread.currentThread().getId());
    }


    @Test
    public void test8() {
        System.out.println("test88888-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test9() {
        System.out.println("test99999-Thread Id:" + Thread.currentThread().getId());
    }

    @Test
    public void test0() {
        System.out.println("test00000-Thread Id:" + Thread.currentThread().getId());
    }
}
