package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Vote;

/**
 * @Author: Usher
 * @Description:
 */
public interface VoteService {

    /**
     * 根据id获取Vote
     * @param id
     * @return
     */
    Vote getVoteById(Long id);

    /**
     * 删除Vote
     * @param id
     */
    void removeVote(Long id);
}
