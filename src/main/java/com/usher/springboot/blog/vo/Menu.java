package com.usher.springboot.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 *
 * 菜单 值对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {

    private static final long serialVersionUID = 5125446818215849983L;

    private String name;
    private String url;


}
