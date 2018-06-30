package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Authority;

/**
 * @Author: Usher
 * @Description:
 */
public interface AuthorityService {

    /**
     * 根据id获取Authority
     * @param id
     * @return
     */
    Authority getAuthorityById(Long id);
}
