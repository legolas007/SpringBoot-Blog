package com.usher.springboot.blog.controller;

import com.usher.springboot.blog.Entities.Category;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.service.CategoryService;
import com.usher.springboot.blog.util.ConstraintViolationExceptionHandler;
import com.usher.springboot.blog.vo.CategoryVO;
import com.usher.springboot.blog.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Controller
@RequestMapping("/catalogs")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 获取分类列表
     * @param username
     * @param model
     * @return
     */
    @GetMapping
    public String listComments(@RequestParam(value = "username", required = true) String username, Model model) {
        User user = (User)userDetailsService.loadUserByUsername(username);
        List<Category> catalogs = categoryService.listCategories(user);

        //判断操作用户是否是分类的所有者
        boolean isOwner = false;

        if (SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (principal != null && user.getUsername().equals(principal.getUsername())) {
                isOwner = true;
            }
        }

        model.addAttribute("isCatalogsOwner", isOwner);
        model.addAttribute("catalogs", catalogs);
        return "/userspace/u :: #catalogRepleace";

    }

    /**
     * 创建
     * @param categoryVO
     * @return
     */
    @PostMapping
    @PreAuthorize("authentication.name.equals(#categoryVO.username)")// 指定用户才能操作方法
    public ResponseEntity<ResponseVO> create(@RequestBody CategoryVO categoryVO) {
        String username = categoryVO.getUsername();
        Category category = categoryVO.getCategory();

        User user = (User) userDetailsService.loadUserByUsername(username);

        try {
            category.setUser(user);
            categoryService.saveCategory(category);
        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new ResponseVO(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", null));
    }

    /**
     * 删除
     * @param username
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<ResponseVO> delete(String username, @PathVariable("id") Long id) {
        try {
            categoryService.removeCategory(id);
        }catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new ResponseVO(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
        }

        return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", null));
    }
    /**
     * 获取分类编辑界面
     * @param model
     * @return
     */
    @GetMapping("/edit")
    public String getCatalogEdit(Model model) {
        Category category = new Category(null, null);
        model.addAttribute("catalog",category);
        return "/userspace/catalogedit";
    }
    /**
     * 根据 Id 获取分类信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String getCatalogById(@PathVariable("id") Long id, Model model) {
        Category catalog = categoryService.getCategoryById(id);
        model.addAttribute("catalog",catalog);
        return "/userspace/catalogedit";
    }
}
