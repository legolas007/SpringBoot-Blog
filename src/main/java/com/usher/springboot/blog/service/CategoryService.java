package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Category;
import com.usher.springboot.blog.Entities.User;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 * 类别管理
 */
public interface CategoryService {

    Category saveCategory(Category category);

    void removeCategory(Long id);

    Category getCategoryById(Long id);

    List<Category> listCategories(User user);

}
