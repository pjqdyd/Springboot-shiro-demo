package com.pjqdyd.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/userApi")
@Controller
public class UserController {


    /**
     * 测试的主页
     */
    @GetMapping("/index")
    public ModelAndView index(Model model){
        model.addAttribute("title", "测试主页");
        return new ModelAndView("index");
    }

    /**
     * 登录的html页面
     */
    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    /**
     * 用户添加的html页面
     */
    @GetMapping("/add")
    public String add(){
        return "/user/add";
    }

    /**
     * 用户更新的html页面
     */
    @GetMapping("/update")
    public String update(){
        return "/user/update";
    }

    /**
     * 登录的处理逻辑接口
     */
    @PostMapping("/loginApi")
    public String loginApi(String name, String password, Model model){

        /**
         * 使用shiro编写认证操作
         */
        //1.获取一个主体对象
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken uPToken = new UsernamePasswordToken(name, password);

        //3.执行登录方法(通过是否有异常来判断成功失败)
        try {
            subject.login(uPToken);                       //这个方法会交给我们CustomRealm的认证方法来处理
            return "redirect:/userApi/index";             //登录成功, 重定向到主页
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "/login";                              //登录失败,用户名不存在,返回登录页html
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "/login";                              //登录失败,密码错误,返回登录页html
        }

    }

}
