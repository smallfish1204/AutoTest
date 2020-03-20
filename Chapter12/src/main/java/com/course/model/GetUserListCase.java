package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {

    private Integer id;
    private String username;
    private Integer age;
    private String sex;
    private String expected;
}
