package com.usher.springboot.blog.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * 权限.
 */
@Entity // 实体
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 7473788746273568636L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@Column(nullable = false) // 映射为字段，值不能为空
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
