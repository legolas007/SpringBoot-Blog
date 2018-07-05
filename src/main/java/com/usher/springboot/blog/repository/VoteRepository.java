package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Usher
 * @Description:
 */
public interface VoteRepository extends JpaRepository<Vote,Long> {
}
