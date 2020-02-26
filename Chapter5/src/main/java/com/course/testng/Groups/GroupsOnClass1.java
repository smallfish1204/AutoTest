package com.course.testng.Groups;

import org.testng.annotations.Test;

@Test(groups = "student")
public class GroupsOnClass1 {
    public void student1() {
        System.out.println("GroupsOnClass1中的student11111运行");
    }

    public void student2() {
        System.out.println("GroupsOnClass1中的student22222运行");
    }
}
