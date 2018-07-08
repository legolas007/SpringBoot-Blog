package com.usher.springboot.blog.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import com.usher.springboot.blog.service.CommentService;
import com.usher.springboot.blog.util.ConstraintViolationExceptionHandler;
import com.usher.springboot.blog.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.usher.springboot.blog.entities.Blog;
import com.usher.springboot.blog.entities.Comment;
import com.usher.springboot.blog.entities.User;
import com.usher.springboot.blog.service.BlogService;


/**
 * 评论 控制器.
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 获取评论列表
	 * @param blogId
	 * @param model
	 * @return
	 */
	@GetMapping
	public String listComments(@RequestParam(value="blogId",required=true) Long blogId, Model model) {
		Blog blog = blogService.getBlogById(blogId);
		List<Comment> comments = blog.getComments();
		
		// 判断操作用户是否是评论的所有者
		String commentOwner = "";
		if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				 &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			if (principal !=null) {
				commentOwner = principal.getUsername();
			} 
		}
		
		model.addAttribute("commentOwner", commentOwner);
		model.addAttribute("comments", comments);
		return "/userspace/blog :: #mainContainerRepleace";
	}
	/**
	 * 发表评论
	 * @param blogId
	 * @param commentContent
	 * @return
	 */
	@PostMapping
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
	public ResponseEntity<ResponseVO> createComment(Long blogId, String commentContent) {
 
		try {
			blogService.createComment(blogId, commentContent);
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new ResponseVO(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
		}
		
		return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", null));
	}
	
	/**
	 * 删除评论
	 * @return
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")  // 指定角色权限才能操作方法
	public ResponseEntity<ResponseVO> delete(@PathVariable("id") Long id, Long blogId) {
		
		boolean isOwner = false;
		User user = commentService.getCommentById(id).getUser();
		
		// 判断操作用户是否是评论的所有者
		if (SecurityContextHolder.getContext().getAuthentication() !=null && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				 &&  !SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser")) {
			User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
			if (principal !=null && user.getUsername().equals(principal.getUsername())) {
				isOwner = true;
			} 
		} 
		
		if (!isOwner) {
			return ResponseEntity.ok().body(new ResponseVO(false, "没有操作权限"));
		}
		
		try {
			blogService.removeComment(blogId, id);
			commentService.removeComment(id);
		} catch (ConstraintViolationException e)  {
			return ResponseEntity.ok().body(new ResponseVO(false, ConstraintViolationExceptionHandler.getMessage(e)));
		} catch (Exception e) {
			return ResponseEntity.ok().body(new ResponseVO(false, e.getMessage()));
		}
		
		return ResponseEntity.ok().body(new ResponseVO(true, "处理成功", null));
	}
}
