package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
