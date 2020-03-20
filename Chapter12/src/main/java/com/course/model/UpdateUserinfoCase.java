package com.course.model;

import lombok.Data;

@Data
public class UpdateUserinfoCase {

    private Integer id;
    private Integer userid;
    private String username;
    private String sex;
    private Integer age;
    private Integer permission;
    private Integer is_delete;
    private String expected;
}
