package com.usher.springboot.blog.controller;

import java.util.ArrayList;
import java.util.List;

import com.usher.springboot.blog.vo.MenuVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户控制器.
 */
@Controller
@RequestMapping("/admins")
public class AdminController {

	/**
	 * 获取后台管理主页面
	 * @return
	 */
	@GetMapping
	public ModelAndView listUsers(Model model) {
		List<MenuVO> list = new ArrayList<>();
		list.add(new MenuVO("用户管理", "/users"));
		model.addAttribute("list", list);
		return new ModelAndView("/admins/index", "model", model);
	}
 
	 
}
