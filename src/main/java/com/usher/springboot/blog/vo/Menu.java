package com.usher.springboot.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 *
 * 菜单 值对象
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 5125446818215849983L;

    private String name;
    private String url;
    public Menu(String name, String url) {
        this.name = name;
        this.url = url;
    }

}
