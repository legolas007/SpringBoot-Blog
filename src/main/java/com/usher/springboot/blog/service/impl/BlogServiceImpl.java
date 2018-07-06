package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.*;
import com.usher.springboot.blog.repository.BlogRepository;
import com.usher.springboot.blog.repository.VoteRepository;
import com.usher.springboot.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class BlogServiceImpl  implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.delete(id);
    }

    @Transactional
    @Override
    public Blog updateBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleLike(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleLikeAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findOne(id);
        blog.setReadSize(blog.getCommentSize()+1);
        blogRepository.save(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog blog = blogRepository.findOne(blogId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Comment comment = new Comment(user, commentContent);
        blog.addComment(comment);

        return blogRepository.save(blog);
    }

    @Override
    public void removeComment(Long blogId, Long id) {

        Blog blog = blogRepository.findOne(blogId);
        blog.removeComment(id);
        blogRepository.save(blog);
    }

    @Override
    public void createVote(Long blogId) {
        Blog blog = blogRepository.findOne(blogId);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Vote vote = new Vote(user);

        boolean isExist = blog.addVote(vote);

        if (isExist) {
            throw new IllegalArgumentException("已点过赞");
        }

        blogRepository.save(blog);
    }

    @Override
    public void removeVote(Long blogId, Long voteId) {
        Blog blog = blogRepository.findOne(blogId);
        blog.removeVote(voteId);
        blogRepository.save(blog);
    }

    @Override
    public Page<Blog> listBlogsByCategory(Category category, Pageable pageable) {
        Page<Blog> blogs = blogRepository.findByCategory(category, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        // 模糊查询
        title = "%" + title + "%";
        //Page<Blog> blogs = blogRepository.findByUserAndTitleLikeOrderByCreateTimeDesc(user, title, pageable);
        String tags = title;
        Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,user, tags,user, pageable);
        return blogs;
    }
}
