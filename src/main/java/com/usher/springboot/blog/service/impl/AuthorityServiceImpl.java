/**
 * 
 */
package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.entities.Authority;
import com.usher.springboot.blog.repository.AuthorityRepository;
import com.usher.springboot.blog.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Authority 服务.
 *
 */
@Service
@Slf4j
public class AuthorityServiceImpl  implements AuthorityService {
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}

}
