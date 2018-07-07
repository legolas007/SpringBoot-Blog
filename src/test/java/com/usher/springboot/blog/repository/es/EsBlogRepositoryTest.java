package com.usher.springboot.blog.repository.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Test
    public void findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContainingOrTagsContaining() {
    }

    @Test
    public void findByBlogId() {

    }
}