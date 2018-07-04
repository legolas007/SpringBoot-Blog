package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Blog;
import com.usher.springboot.blog.repository.BlogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceImplTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void saveBlog() {
    }

    @Test
    public void removeBlog() {
    }

    @Test
    public void updateBlog() {
    }

    @Test
    public void getBlogById() {
        Blog blog = blogRepository.findOne(1L);
        Assert.assertNotNull(blog);
        System.out.println(blog.getReadSize());
    }

    @Test
    public void listBlogsByTitleLike() {
    }

    @Test
    public void listBlogsByTitleLikeAndSort() {
    }

    @Test
    public void readingIncrease() {
        Blog blog = blogRepository.findOne(1L);
        blog.setReadSize(blog.getReadSize() + 1);
        blogRepository.save(blog);
        System.out.println(blog.getReadSize());
    }
}