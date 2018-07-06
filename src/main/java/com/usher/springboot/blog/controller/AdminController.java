package com.usher.springboot.blog.controller;

import com.usher.springboot.blog.vo.MenuVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


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
        List<MenuVO> list = new ArrayList<>();
        list.add(new MenuVO("用户管理", "/users"));
        list.add(new MenuVO("角色管理", "/roles"));
        list.add(new MenuVO("博客管理", "/blogs"));
        list.add(new MenuVO("评论管理", "/commits"));
        model.addAttribute("list", list);
        return new ModelAndView("/admins/index","model",model);
    }

}
