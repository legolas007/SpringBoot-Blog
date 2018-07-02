package com.usher.springboot.blog.repository;

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
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void findByUserAndTitleLikeOrderByCreateTimeDesc() {

    }

    @Test
    public void findByUserAndTitleLike() {

    }
}