package com.usher.springboot.blog.controller;

import com.usher.springboot.blog.Entities.Authority;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.service.AuthorityService;
import com.usher.springboot.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 * 主页控制器
 */
@Controller
@Slf4j
public class MainController {

    private static final Long ROLE_USER_AUTHORITY_ID = 2L;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;


    @GetMapping("/")
    public String root(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError", true);
        model.addAttribute("errorMsg", "登录失败");
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(@Valid User user,
                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("参数错误！");
        }
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(ROLE_USER_AUTHORITY_ID));
        user.setAuthorities(authorities);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}
