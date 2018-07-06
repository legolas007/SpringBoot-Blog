package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Category;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.repository.CategoryRepository;
import com.usher.springboot.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {

        List<Category> list = categoryRepository.findByUserAndName(category.getUser(), category.getName());

        if (list != null && list.size() > 0) {
            throw new IllegalArgumentException("该分类已经存在");
        }

        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Long id) {
        categoryRepository.delete(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> listCategories(User user) {
        return categoryRepository.findByUser(user);
    }
}
