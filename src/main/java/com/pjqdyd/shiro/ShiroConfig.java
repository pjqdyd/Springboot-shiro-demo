package com.pjqdyd.shiro;


import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**创建Realm关联数据的bean,获取用户提供的数据(角色,权限信息等),方便给Manager管理**/
    @Bean
    public CustomRealm getRealm(){
        return new CustomRealm();
    }

    /**创建DefaultWebSecurityManager, 默认的安全管理器bean**/
    @Bean
    public DefaultWebSecurityManager getSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联上面的Realm域
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    /**创建ShiroFilterFactoryBean,过滤器的bean**/
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //使用上面的安全管理器
        shiroFilterFactoryBean.setSecurityManager(getSecurityManager());

        //添加Shiro的内置过滤器,实现权限相关的的拦截器
        /**认证:
         * anon:  无需认证(登录)就可以访问
         * authc: 必须认证才可以访问
         * user:  如果使用了rememberMe的功能就可以访问
         *
         * 授权:
         * perms: 必须得到权限才可以访问
         * role:  必须得到角色权限才可以访问
         */
        //使用有序的Map集合来装载url资源与权限
        Map<String, String> filterMap = new LinkedHashMap<>();

        //认证过滤
        filterMap.put("/userApi/index", "anon");
        filterMap.put("/userApi/loginApi", "anon");

        //授权过滤, "user:add"代表授权字符串
        filterMap.put("/userApi/add", "perms[user:add]");
        filterMap.put("/userApi/update", "perms[user:update]");

        filterMap.put("/userApi/*", "authc");

        shiroFilterFactoryBean.setLoginUrl("/userApi/login");          //设置默认的登录跳转页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/userApi/noAuth");  //设置默认未授权跳转的页面

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

}
