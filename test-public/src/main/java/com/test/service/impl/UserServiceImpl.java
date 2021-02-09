package com.test.service.impl;

import com.test.entity.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查询所有
    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return userMapper.queryAll();
    }

}
