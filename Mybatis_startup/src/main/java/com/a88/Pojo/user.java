package com.a88.Pojo;

import lombok.Data;

@Data
public class user {
    private Integer id;
    private String name;
    private Short age;
    private Short gender;
    private String phone;


    public user() {
    }

    public user(Integer id, String name, Short age, Short gender, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
    }


}
