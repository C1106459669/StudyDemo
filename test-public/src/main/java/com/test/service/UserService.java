package com.test.service;

import com.test.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    //查询所有
    List<User> selectAll();

    List<Map<String, Object>> queryAll();

}
