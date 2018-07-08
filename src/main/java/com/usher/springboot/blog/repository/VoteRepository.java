package com.usher.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usher.springboot.blog.entities.Vote;

/**
 * Vote 仓库.
 *
 */
public interface VoteRepository extends JpaRepository<Vote, Long>{
 
}
