package com.github.reels_project.reels.faces.model.datatable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DatatableColumn {
	
	public DatatableColumn(){
//		this.key = "";
//		this.name = "";
	}
	
	
	public DatatableColumn(String key, String name, String width) {
		super();
		this.key = key;
		this.name = name;
		this.width = width;
	}


	private String key;
	private String name;
	private String width;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
}
