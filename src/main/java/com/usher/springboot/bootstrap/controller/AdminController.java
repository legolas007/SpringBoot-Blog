package com.usher.springboot.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: Usher
 * @Description:
 * 用户控制器
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

    /**
     * 获取后台管理主页面
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView listUsers(Model model) {

        return new ModelAndView("admins/index","menuList",model);
    }

}
