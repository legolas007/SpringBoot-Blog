package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Authority;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.service.AuthorityService;
import com.usher.springboot.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserService userService;

    @Test
    public void findByUserAndTitleLikeOrderByCreateTimeDesc() {

    }

    @Test
    public void findByUserAndTitleLike() {
        User user = new User("Jhon", "242199392@qq.com", "Jhon", "123456");
        userRepository.save(user);
        System.out.println(userRepository.findByUsername("Jhon"));
    }

    @Test
    public void test(){
        User user = new User("WTF", "2421993@qq.com", "WTF", "123456");
        Long authorityId = 1L;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authorityService.getAuthorityById(authorityId));
        user.setAuthorities(authorities);

        //新用户
        if (user.getId() == null) {
            //密码加密
            user.setEncodePassword(user.getPassword());
        }else {
            //用户信息更改
            User originalUser = userService.getUserById(user.getId());
            String rawPassword = originalUser.getPassword();
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePasswd = encoder.encode(user.getPassword());

            boolean isMatch = encoder.matches(rawPassword, encodePasswd);

            if (!isMatch) {
                user.setEncodePassword(user.getPassword());
            }else {
                user.setPassword(user.getPassword());
            }
        }

        try {
            userService.saveUser(user);
        } catch (ConstraintViolationException e) {
            System.out.println("Exception");
        }
        System.out.println("Success");
    }
}