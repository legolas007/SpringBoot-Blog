package com.usher.springboot.blog.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: Usher
 * @Description:
 * 分类管理
 */
@Entity
@Data
public class Catalog implements Serializable {

    private static final long serialVersionUID = 3192813679438245410L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty(message = "不能为空")
    @Size(min = 2,max = 30)
    @Column(nullable = false)//映射字段
    private String name;

    public Catalog() {
    }

    public Catalog(User user, String name) {
        this.user = user;
        this.name = name;
    }
}
