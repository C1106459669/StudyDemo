package com.test.mapper;

import com.test.entity.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    //查询所有
    List<User> selectAll();

    List<Map<String, Object>> queryAll();

}
