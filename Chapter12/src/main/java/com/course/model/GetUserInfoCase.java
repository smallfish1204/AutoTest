package com.course.model;

import lombok.Data;

@Data
public class GetUserInfoCase {

    private Integer id;
    private Integer userid;
    private String expected;
}
