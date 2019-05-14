package com.pjqdyd.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义的Realm,提供(角色,权限信息等)数据给shiro配置
 */
public class CustomRealm extends AuthorizingRealm {

    /**认证的方法**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证方法");

        //假设数据库存在用户名和密码
        String name = "pjqdyd";
        String password = "123456";

        //编写shiro认证逻辑
        //1.拿到controller层传入的用户信息
        UsernamePasswordToken uPToken = (UsernamePasswordToken)authenticationToken;

        //2.判断用户名是否和数据库的匹配
        if (!uPToken.getUsername().equals(name)){ //用户不存在
            return null;                          //shiro底层会抛出UnKnowAccountException
        }

        //3.判断密码,交给shiro底层判断
        return new SimpleAuthenticationInfo("",password,"");
    }

    /**授权的方法**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权方法");
        return null;
    }
}
