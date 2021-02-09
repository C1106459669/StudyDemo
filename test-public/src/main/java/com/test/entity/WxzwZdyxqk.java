package com.test.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (WxzwZdyxqk)实体类
 *
 * @author LGCSpadger
 * @since 2021-01-02 09:24:19
 */
@Data
public class WxzwZdyxqk implements Serializable {

    private static final long serialVersionUID = -32073321610178863L;
    private Integer id;
    private String areaName;
    private Integer termNum;
    private String wxzwZdzxl;
    private String yxZdzxl;

}