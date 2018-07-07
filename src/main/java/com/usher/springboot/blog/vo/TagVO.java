package com.usher.springboot.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 */
@Data
public class TagVO implements Serializable {

    private static final long serialVersionUID = 5345682903317226547L;

    private String name;
    private Long count;

    public TagVO(String name, Long count) {
        this.name = name;
        this.count = count;
    }
}
