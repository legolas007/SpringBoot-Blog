package com.usher.springboot.blog.vo;

import java.io.Serializable;

/**
 * 菜单 值对象.
 *
 */
public class MenuVO implements Serializable{

	private static final long serialVersionUID = -8838537658424538112L;
	
	private String name;
	private String url;
	
	public MenuVO(String name, String url) {
		this.name = name;
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
