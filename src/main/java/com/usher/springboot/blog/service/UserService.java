package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 * User 服务接口
 */
public interface UserService {

    User saveUser(User user);

    void removeUser(Long id);

    void removeUsersInBatch(List<User> users);

    User updateUser(User user);

    User getUserById(Long id);

    /**
     * 获取用户列表
     * @return
     */
    List<User> listUsers();

    /**
     * 根據用戶名進行分頁模糊查詢
     * @param name
     * @param pageable
     * @return
     */
    Page<User> listUsersByNameLike(String name, Pageable pageable);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
