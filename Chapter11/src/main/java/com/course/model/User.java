package com.course.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Integer id;
    private String username;
    private String sex;
    private Integer age;
    private String school;
    private Timestamp create_time;
    private Timestamp update_time;
}
