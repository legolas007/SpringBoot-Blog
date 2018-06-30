package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Usher
 * @Description:
 * Authority 仓库
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long> {

}
