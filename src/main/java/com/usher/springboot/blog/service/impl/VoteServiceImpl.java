package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Vote;
import com.usher.springboot.blog.repository.VoteRepository;
import com.usher.springboot.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

    @Transactional
    @Override
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }
}
