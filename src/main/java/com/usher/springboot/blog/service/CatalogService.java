package com.usher.springboot.blog.service;

import com.usher.springboot.blog.Entities.Catalog;
import com.usher.springboot.blog.Entities.User;

import java.util.List;

/**
 * @Author: Usher
 * @Description:
 * 类别管理
 */
public interface CatalogService {

    Catalog saveCatalog(Catalog catalog);

    void removeCatalog(Long id);

    Catalog getCatalogById(Long id);

    List<Catalog> listCatalogs(User user);

}
