package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Authority;
import com.usher.springboot.blog.repository.AuthorityRepository;
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
public class AuthorityServiceImplTest {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Test
    public void getAuthorityById() {
        Authority authority = authorityRepository.findOne(1L);
        System.out.println(authority.getAuthority());
        Assert.assertNotNull(authority);
    }
}