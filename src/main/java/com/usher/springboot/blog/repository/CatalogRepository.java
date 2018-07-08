package com.usher.springboot.blog.repository;

import com.usher.springboot.blog.Entities.Catalog;
import com.usher.springboot.blog.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
public interface CatalogRepository extends JpaRepository<Catalog, Long> {

    /**
     * 通过user查找
     * @param user
     * @return
     */
    List<Catalog> findByUser(User user);

    /**
     * 通过user和name查找
     * @param user
     * @param name
     * @return
     */
    List<Catalog> findByUserAndName(User user, String name);
}
