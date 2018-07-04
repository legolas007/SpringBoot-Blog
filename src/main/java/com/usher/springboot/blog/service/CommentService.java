package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Comment;

/**
 * @Author: Usher
 * @Description:
 */
public interface CommentService {

    /**
     * 根据id获取comment
     * @param id
     * @return
     */
    Comment getCommentById(Long id);

    /**
     * 根据id，删除评论
     * @param id
     */
    void removeComment(Long id);
}
