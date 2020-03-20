package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private Integer permission;
    private Integer is_delete;
    private String expected;
}
