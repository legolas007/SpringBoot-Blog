package com.usher.springboot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Usher
 * @Description:
 * 主页控制器
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    @GetMapping
    public String listBlogs(@RequestParam(value = "order", required = false, defaultValue = "new") String order,
                            @RequestParam(value = "tag", required = false) Long tag) {
        System.out.println("order:" + order + ";tag:" + tag);
        return "redirect:/index?order=" + order + "&tag=" + tag;
    }


}
