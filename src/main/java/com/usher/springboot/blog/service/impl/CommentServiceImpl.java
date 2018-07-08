package com.usher.springboot.blog.service.impl;

import javax.transaction.Transactional;

import com.usher.springboot.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usher.springboot.blog.entities.Comment;
import com.usher.springboot.blog.repository.CommentRepository;

/**
 * Comment 服务.
 *
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;
	/* (non-Javadoc)
	 * @see CommentService#removeComment(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeComment(Long id) {
		commentRepository.delete(id);
	}
	@Override
	public Comment getCommentById(Long id) {
		return commentRepository.findOne(id);
	}

}
