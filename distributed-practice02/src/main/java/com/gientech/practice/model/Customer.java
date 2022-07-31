package com.gientech.practice.model;

import lombok.Data;

import java.util.Date;

@Data
public class Customer {
    private Long id;
    private String name;
    private String phone;
    private String member;
    private Date createTime;
    private Date updateTime;
}
