package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Authority;
import com.usher.springboot.blog.repository.AuthorityRepository;
import com.usher.springboot.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Usher
 * @Description: Authority服务
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findOne(id);
    }
}
