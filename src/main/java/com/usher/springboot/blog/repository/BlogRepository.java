package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Blog;
import com.usher.springboot.blog.Entities.Catalog;
import com.usher.springboot.blog.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Usher
 * @Description:
 */
public interface BlogRepository extends JpaRepository<Blog,Long> {
    /**
     * 根据用户名分页查询用户列表
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLikeOrderByCreateTimeDesc(User user, String title, Pageable pageable);

    /**
     * 根据用户名分页查询用户列表
     * @param user
     * @param title
     * @param pageable
     * @return
     */
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    Page<Blog> findByCatalog(Catalog catalog, Pageable pageable);

    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title, User user, String tags, User user1, Pageable pageable);
}
