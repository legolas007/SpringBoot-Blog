package com.usher.springboot.blog.vo;


import java.io.Serializable;

/**
 * Tag 值对象.
 */
public class TagVO implements Serializable {

	private static final long serialVersionUID = 3395753987764581105L;
	
	private String name;
	private Long count;
	
	public TagVO(String name, Long count) {
		this.name = name;
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
 
}
