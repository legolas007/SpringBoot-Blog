package com.usher.springboot.blog.vo;

import com.usher.springboot.blog.Entities.Catalog;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 * 类别返回列表
 */
@Data
public class CatalogVO implements Serializable {


    private static final long serialVersionUID = -5257075951771468196L;

    private String username;

    private Catalog catalog;

    public CatalogVO() {
    }

    public CatalogVO(String username, Catalog catalog) {
        this.username = username;
        this.catalog = catalog;
    }
}
