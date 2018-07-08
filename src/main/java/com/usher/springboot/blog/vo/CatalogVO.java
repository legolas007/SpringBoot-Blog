package com.usher.springboot.blog.vo;


import java.io.Serializable;

import com.usher.springboot.blog.entities.Catalog;

public class CatalogVO implements Serializable {

	private static final long serialVersionUID = -533979319158673684L;
	
	private String username;
	private Catalog catalog;
	
	public CatalogVO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

}
