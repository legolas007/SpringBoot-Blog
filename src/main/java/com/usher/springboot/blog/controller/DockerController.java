package com.usher.springboot.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Usher
 * @Description:
 */
@Controller
@RequestMapping("/docker")
public class DockerController {
    @GetMapping
    public String dockerTest() {
        return "hello docker!";
    }
}
