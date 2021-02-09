package com.test.controller;

import com.test.entity.User;
import com.test.service.UserService;
import com.test.utils.RedisUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author LGCSpadger
 * @since 2021-02-07 11:33:17
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    @GetMapping("getAll")
    public List<User> getAll(User user) {
        return this.userService.getAll(user);
    }

    @GetMapping("test")
    public List<User> test(User user) {
        List<User> list = userService.getAll(user);
        tt(list);
        List<User> result = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                redisUtils.set("key" + list.get(i).getId(),list.get(i));
                result.add((User) redisUtils.get("key" + list.get(i).getId()));
            }
        }
        return result;
    }

    private void tt(List<T> data) {
        String name = data.get(0).getClass().getName();
        System.out.println(name);
    }

}