package com.pjqdyd.service.impl;

import com.pjqdyd.dao.UserMapper;
import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User的service接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**通过name查找**/
    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    /**通过id查找**/
    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
