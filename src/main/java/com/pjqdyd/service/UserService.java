package com.pjqdyd.service;

import com.pjqdyd.pojo.User;

/**
 * User的service层的接口
 */
public interface UserService {

    /**通过name查询**/
    User findByName(String name);

    /**通过id查询**/
    User findById(Integer id);
}
