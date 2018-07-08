package com.usher.springboot.blog.service;

import com.usher.springboot.blog.entities.Vote;

/**
 * Vote 服务接口.
 *
 */
public interface VoteService {
	/**
	 * 根据id获取 Vote
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);
	/**
	 * 删除Vote
	 * @param id
	 * @return
	 */
	void removeVote(Long id);
}
