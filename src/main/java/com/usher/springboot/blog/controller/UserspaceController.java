package com.usher.springboot.blog.controller;

import com.usher.springboot.blog.Entities.Blog;
import com.usher.springboot.blog.Entities.Category;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.service.BlogService;
import com.usher.springboot.blog.service.CategoryService;
import com.usher.springboot.blog.service.UserService;
import com.usher.springboot.blog.util.ConstraintViolationExceptionHandler;
import com.usher.springboot.blog.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 * 用户主页空间控制器
 */
@Controller
@RequestMapping("/u")
public class UserspaceController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{username}")
    public String userSpace(@PathVariable("username") String username, Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "redirect:/u/" + username + "/blogs";
    }

    /**
     * 用户资料
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView profile(@PathVariable("username") String username,
                                Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/profile", "userModel", model);
    }

    /**
     * 保存个人设置
     * @param user
     * @return
     */
    @PostMapping("/{username}/profile")
    @PreAuthorize("authentication.name.equals(#username)")
    public String saveProfile(@PathVariable("username") String username,User user) {
        User originalUser = userService.getUserById(user.getId());
        originalUser.setEmail(user.getEmail());
        originalUser.setName(user.getName());

        // 判断密码是否做了变更
        String rawPassword = originalUser.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePasswd = encoder.encode(user.getPassword());
        boolean isMatch = encoder.matches(rawPassword, encodePasswd);
        if (!isMatch) {
            originalUser.setEncodePassword(user.getPassword());
        }

        userService.saveUser(originalUser);
        return "redirect:/u/" + username + "/profile";
    }


    /**
     * 获取编辑头像的界面
     * @param username
     * @param model
     * @return
     */
    @GetMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ModelAndView avatar(@PathVariable("username") String username, Model model) {

        User user = (User) userDetailsService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return new ModelAndView("/userspace/avatar", "userModel", model);
    }

    /**
     * 编辑头像
     * @param username
     * @param user
     * @return
     */
    @PostMapping("/{username}/avatar")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<ResponseVO> saveAvatar(@PathVariable("username") String username,
                                                 User user) {
        String avatarUrl = user.getAvatar();
        User originalUser = userService.getUserById(user.getId());
        originalUser.setAvatar(avatarUrl);
        userService.saveUser(originalUser);

        return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", avatarUrl));
    }

    /**
     * 博客
     * @param username
     * @param order
     * @param categoryId
     * @param keyword
     * @return
     */
    @GetMapping("/{username}/blogs")
    public String listBlogsByOrder(@PathVariable("username") String username,
                                   @RequestParam(value="order",required=false,defaultValue="new") String order,
                                   @RequestParam(value="catalog",required=false ) Long categoryId,
                                   @RequestParam(value="keyword",required=false,defaultValue="" ) String keyword,
                                   @RequestParam(value="async",required=false) boolean async,
                                   @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                                   @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize,
                                   Model model) {
        User  user = (User)userDetailsService.loadUserByUsername(username);

        Page<Blog> page = null;

        if (categoryId != null && categoryId > 0) { // 分类查询
            Category category = categoryService.getCategoryById(categoryId);
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = blogService.listBlogsByCategory(category, pageable);
            order = "";
        } else if (order.equals("hot")) { // 最热查询
            Sort sort = new Sort(Sort.Direction.DESC,"readSize","commentSize","voteSize");
            Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
            page = blogService.listBlogsByTitleVoteAndSort(user, keyword, pageable);
        } else if (order.equals("new")) { // 最新查询
            Pageable pageable = new PageRequest(pageIndex, pageSize);
            page = blogService.listBlogsByTitleVote(user, keyword, pageable);
        }


        List<Blog> list = page.getContent();	// 当前所在页面数据列表

        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("catalogId", categoryId);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("blogList", list);
        return (async==true?"/userspace/u :: #mainContainerRepleace":"/userspace/u");
    }
    /**
     * 博客详情
     * @param id
     * @return
     */
    @GetMapping("/{username}/blogs/{id}")
    public String getBlogById(@PathVariable("username")String username,
                              @PathVariable("id")Long id,Model model){
        //点击量
        blogService.readingIncrease(id);
        boolean isBlogOwner = false;
// 判断操作用户是否是博客的所有者
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")){
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user != null && username.equals(user.getUsername())) {
                isBlogOwner = true;
            }
        }

        model.addAttribute("isBlogOwner", isBlogOwner);
        model.addAttribute("blogModel", blogService.getBlogById(id));
        System.out.println("blogId: " + id);
        return "/userspace/blog";
    }

    /**
     * 删除博客
     * @param id
     * @param username
     * @return
     */
    @DeleteMapping("/{username}/blogs/{id}")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<ResponseVO> deleteBlog(@PathVariable("username") String username, @PathVariable("id") Long id) {

        try {
            blogService.removeBlog(id);
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
        }

        String redirectUrl = "/u/" + username + "/blogs";
        return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", redirectUrl));
    }
    /**
     * 获取新增博客的界面
     * @param model
     * @return
     */
    @GetMapping("/{username}/blogs/edit")
    public ModelAndView createBlog(@PathVariable("username")String username, Model model) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        List<Category> categories = categoryService.listCategories(user);

        model.addAttribute("blog", new Blog(null, null, null));
        model.addAttribute("catalogs", categories);
        return new ModelAndView("/userspace/blogedit", "blogModel", model);
    }

    /**
     * 获取编辑博客的界面
     * @param model
     * @return
     */
    @GetMapping("/{username}/blogs/edit/{id}")
    public ModelAndView editBlog(@PathVariable("username") String username,@PathVariable("id") Long id, Model model) {
        //获取用户分类列表
        User user = (User) userDetailsService.loadUserByUsername(username);
        List<Category> categories = categoryService.listCategories(user);

        model.addAttribute("blog", blogService.getBlogById(id));
        model.addAttribute("catalogs", categories);
        return new ModelAndView("/userspace/blogedit", "blogModel", model);
    }

    /**
     * 保存博客
     * @param username
     * @param blog
     * @return
     */
    @PostMapping("/{username}/blogs/edit")
    @PreAuthorize("authentication.name.equals(#username)")
    public ResponseEntity<ResponseVO> saveBlog(@PathVariable("username") String username, @RequestBody Blog blog) {

        //如果分类为空
        if (blog.getCategory().getId() == null) {
            return ResponseEntity.ok().body(new ResponseVO(false, "未选择分类"));
        }

/*        User user = (User) userDetailsService.loadUserByUsername(username);
        blog.setUser(user);*/
        try {
            //修改还是新增
            if (blog.getId() != null) {
                Blog orignalBlog = blogService.getBlogById(blog.getId());
                orignalBlog.setTitle(blog.getTitle());
                orignalBlog.setContent(blog.getContent());
                orignalBlog.setSummary(blog.getSummary());
                orignalBlog.setCategory(blog.getCategory());
                orignalBlog.setTags(blog.getTags());
                blogService.saveBlog(blog);
            } else {
                User user = (User) userDetailsService.loadUserByUsername(username);
                blog.setUser(user);
                blogService.saveBlog(blog);
            }

        } catch (ConstraintViolationException e)  {
            return ResponseEntity.ok().body(new ResponseVO(false, ConstraintViolationExceptionHandler.getMessage(e)));
        } catch (Exception e) {
            return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
        }

        String redirectUrl = "/u/" + username + "/blogs/" + blog.getId();
        return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", redirectUrl));
    }
}
