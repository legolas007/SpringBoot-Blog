package com.usher.springboot.blog.service;

import com.usher.springboot.blog.entities.Authority;

/**
 * Authority 服务接口.
 */
public interface AuthorityService {
	 
	
	/**
	 * 根据id获取 Authority
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
