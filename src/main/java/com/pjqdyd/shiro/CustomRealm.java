package com.pjqdyd.shiro;

import com.pjqdyd.pojo.User;
import com.pjqdyd.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
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
        //将user给principal主角,方便在主体Subject中获取用户(subject.getPrincipal)
        //将数据库用户的密码交给shiro底层判断
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }

    /**授权的方法**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("进入授权方法");

        //给资源授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //添加资源的授权字符串, 当拦截器中的url资源有"user:add"权限时就可以访问url
        //info.addStringPermission("user:add");

        //获取当前的主体(当前的登录主体)
        Subject subject = SecurityUtils.getSubject();
        //获取当前的用户
        User user = (User) subject.getPrincipal();

        //到数据库查询当前用户登录授权的字符串
        User dbUser = userService.findById(user.getId());

        //给用户添加它有的授权字符串,对应的拦截器的perms[dbUser.getPerms()]的接口该用户就可以访问了
        info.addStringPermission(dbUser.getPerms());
        return info;
    }
}
