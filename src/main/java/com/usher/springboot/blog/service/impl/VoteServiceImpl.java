package com.usher.springboot.blog.service.impl;

import javax.transaction.Transactional;

import com.usher.springboot.blog.entities.Vote;
import com.usher.springboot.blog.repository.VoteRepository;
import com.usher.springboot.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Vote 服务.
 *
 */
@Service
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	@Transactional
	public void removeVote(Long id) {
		voteRepository.delete(id);
	}
	@Override
	public Vote getVoteById(Long id) {
		return voteRepository.findOne(id);
	}

}
