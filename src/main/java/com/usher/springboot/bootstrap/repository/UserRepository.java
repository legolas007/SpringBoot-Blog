package com.usher.springboot.bootstrap.repository;

import com.usher.springboot.bootstrap.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户仓库.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
