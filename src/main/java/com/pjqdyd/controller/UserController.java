package com.pjqdyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
