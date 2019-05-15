package com.pjqdyd.shiro;

import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的Realm,提供(角色,权限信息等)数据给shiro配置
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**认证的方法**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证方法");

        //编写shiro认证逻辑
        //1.拿到controller层传入的用户信息
        UsernamePasswordToken uPToken = (UsernamePasswordToken)authenticationToken;

        //2.查询数据库的用户对象
        User user = userService.findByName(uPToken.getUsername());

        //3.判断用户名是否和数据库的匹配
        if (user == null){ //用户不存在
            return null;                          //shiro底层会抛出UnKnowAccountException
        }

        //4.判断密码,交给shiro底层判断
        return new SimpleAuthenticationInfo("",user.getPassword(),"");
    }

    /**授权的方法**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法");
        return null;
    }
}
