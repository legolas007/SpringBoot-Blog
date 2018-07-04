package com.usher.springboot.blog.Entities;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @Author: Usher
 * @Description:
 * 评论实体类
 */
@Entity
@Data
public class Comment implements Serializable {
    private static final long serialVersionUID = 6480745308868159823L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增长
    private Long id;

    @NotEmpty(message = "评论内容不能为空")
    @Size(min=2, max=500)
    @Column(nullable = false) // 映射为字段，值不能为空
    private String content;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false) // 映射为字段，值不能为空
    @org.hibernate.annotations.CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    protected Comment() {
        // TODO Auto-generated constructor stub
    }
    public Comment(User user, String content) {
        this.content = content;
        this.user = user;
    }
}
