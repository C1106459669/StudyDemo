package com.test.controller;

import com.test.entity.WxzwZdyxqk;
import com.test.service.WxzwZdyxqkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * (WxzwZdyxqk)表控制层
 *
 * @author LGCSpadger
 * @since 2021-01-02 09:24:20
 */
@RestController
@RequestMapping("wxzwZdyxqk")
public class WxzwZdyxqkController {

    /**
     * 服务对象
     */
    @Autowired
    private WxzwZdyxqkService wxzwZdyxqkService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public WxzwZdyxqk selectOne(Integer id) {
        return this.wxzwZdyxqkService.queryById(id);
    }

}