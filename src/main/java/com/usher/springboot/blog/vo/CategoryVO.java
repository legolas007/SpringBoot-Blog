package com.usher.springboot.blog.vo;

import com.usher.springboot.blog.Entities.Category;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 * 类别返回列表
 */
@Data
public class CategoryVO implements Serializable {


    private static final long serialVersionUID = -5257075951771468196L;

    private String username;

    private Category category;

    public CategoryVO() {
    }

    public CategoryVO(String username, Category category) {
        this.username = username;
        this.category = category;
    }
}
