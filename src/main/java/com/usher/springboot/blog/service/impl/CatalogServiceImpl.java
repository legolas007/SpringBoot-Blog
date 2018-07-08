package com.usher.springboot.blog.service.impl;

import com.usher.springboot.blog.Entities.Catalog;
import com.usher.springboot.blog.Entities.User;
import com.usher.springboot.blog.repository.CatalogRepository;
import com.usher.springboot.blog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private CatalogRepository catalogRepository;

    @Override
    public Catalog saveCatalog(Catalog catalog) {

        List<Catalog> list = catalogRepository.findByUserAndName(catalog.getUser(), catalog.getName());

        if (list != null && list.size() > 0) {
            throw new IllegalArgumentException("该分类已经存在");
        }

        return catalogRepository.save(catalog);
    }

    @Override
    public void removeCatalog(Long id) {
        catalogRepository.delete(id);
    }

    @Override
    public Catalog getCatalogById(Long id) {
        return catalogRepository.findOne(id);
    }

    @Override
    public List<Catalog> listCatalogs(User user) {
        return catalogRepository.findByUser(user);
    }
}
