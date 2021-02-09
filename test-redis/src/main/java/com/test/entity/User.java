package com.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author LGCSpadger
 * @since 2021-02-07 11:33:13
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 119560701756687166L;
    private Integer id;
    private String username;
    private String password;

}