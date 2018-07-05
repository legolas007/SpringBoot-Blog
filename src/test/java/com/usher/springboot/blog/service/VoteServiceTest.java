package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Vote;
import com.usher.springboot.blog.repository.VoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteServiceTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void getVoteById() {
        Vote vote = voteRepository.findOne(1L);
        System.out.println(vote.getCreateTime());
        Assert.assertNotNull(vote);
    }

    @Test
    public void removeVoteById() {
        voteRepository.delete(1L);
        Vote vote = voteRepository.findOne(1L);
        System.out.println(vote.getCreateTime());
        Assert.assertNull(vote);
    }
}