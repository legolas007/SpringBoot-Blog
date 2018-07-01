package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Usher
 * @Description:
 * Authority 仓库
 */
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
