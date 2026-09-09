package com.guanwx.entity;

import lombok.Data;

import java.math.BigInteger;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2026-01-04 04:08:04
 */

@Data
public class User {

    private BigInteger id;

    private String name;

    private Integer age;

    private String email;

}

