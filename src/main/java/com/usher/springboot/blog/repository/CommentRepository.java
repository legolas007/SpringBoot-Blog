package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Usher
 * @Description:
 */
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
