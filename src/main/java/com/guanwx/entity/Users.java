package com.guanwx.entity;

import lombok.Data;

import java.io.Serializable;

import java.util.Date;

/**
* 
* @TableName users
*/

@Data
public class Users implements Serializable {

    private String id;

    private String username;

    private String usernameNormalized;

    private String email;

    private String emailNormalized;

    private String passwordHash;

    private String passwordAlgorithm;

    private Date passwordChangedAt;

    private Integer mustChangePassword;

    private Integer failedLoginAttempts;

    private Date lockoutUntil;

    private Integer isActive;

    private Date emailVerifiedAt;

    private Date lastLoginAt;

    private Object profileJson;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;


}
