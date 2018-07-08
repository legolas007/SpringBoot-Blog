package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.entities.Vote;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoteRepositoryTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    public void test() {
        Vote vote = voteRepository.findOne(1L);
        System.out.println(vote.getCreateTime());
        Assert.assertNotNull(vote);
    }

}