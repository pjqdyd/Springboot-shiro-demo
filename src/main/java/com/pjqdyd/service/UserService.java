package com.pjqdyd.service;

import com.pjqdyd.pojo.User;

/**
 * User的service层的接口
 */
public interface UserService {

    /**通过name查询**/
    User findByName(String name);

}
