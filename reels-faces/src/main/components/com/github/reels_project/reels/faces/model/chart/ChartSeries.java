package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartSeries implements Serializable{
	private String key;
	private String type;
	private List<Object> data;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Object> getData() {
		return data;
	}
	public void setData(List<Object> data) {
		this.data = data;
	}
	public void add(Object d) {
		this.data.add(d);
	}
	public void remove(Object d) {
		this.data.remove(d);
	}
	public void remove(Integer index) {
		this.data.remove(index);
	}
	public void removeAll() {
		this.data.clear();
	}
	
	public ChartSeries(){
		this.data = Lists.newArrayList();
	}
}
