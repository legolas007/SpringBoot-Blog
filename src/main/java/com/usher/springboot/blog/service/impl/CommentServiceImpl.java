package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Comment;
import com.usher.springboot.blog.repository.CommentRepository;
import com.usher.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findOne(id);
    }
    @Transactional
    @Override
    public void removeComment(Long id) {
        commentRepository.delete(id);
    }
}
