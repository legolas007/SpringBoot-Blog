package com.usher.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usher.springboot.blog.entities.Comment;

/**
 * Comment 仓库.
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
 
}
