package com.course.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String school;
    private Integer permission;
    private Integer is_delete;
    private Timestamp create_time;
    private Timestamp update_time;

    @Override
    public String toString() {
        return ("{id:" + id
                + ",username:" + username
                + ",sex:" + sex
                + ",age:" + age
                + ",school:" + school
                + ",permission:" + permission
                + ",is_delete:" + is_delete
                + ",create_time:" + create_time
                + ",update_time:" + update_time + "}"
        );
    }

}
