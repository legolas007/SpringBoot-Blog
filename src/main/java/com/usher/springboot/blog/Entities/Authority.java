package com.usher.springboot.blog.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * @Author: Usher
 * @Description:
 * 用户权限
 */
@Entity
@Getter
@Setter
public class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长
    private Long id;

    @Column(nullable = false)//字段不能为空
    private String name;



    @Override
    public String getAuthority() {
        return name;
    }
}
