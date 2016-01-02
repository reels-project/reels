package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisX implements Serializable {
	public ChartAxisX(){
		this.show = true;
		this.type = "indexed";
		this.localtime = true;
		this.categories = Lists.newArrayList();
		this.tick = new ChartAxisXTick();
		this.max = null;
		this.max = null;
		this.padding = new ChartAxisXPadding();
		this.height = null;
		this.label = new ChartAxisLabel();
	}
	
	private Boolean show;
	private String type;
	private Boolean localtime;
	private List<String> categories;
	private ChartAxisXTick tick;
	private Double max;
	private Double min;
	private ChartAxisXPadding padding;
	private Double height;
	private ChartAxisLabel label;
	
	public Boolean getShow() {
		return show;
	}
	public ChartAxisXPadding getPadding() {
		return padding;
	}
	public void setPadding(ChartAxisXPadding padding) {
		this.padding = padding;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public void setShow(Boolean show) {
		this.show = show;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getLocaltime() {
		return localtime;
	}
	public void setLocaltime(Boolean localtime) {
		this.localtime = localtime;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public ChartAxisXTick getTick() {
		return tick;
	}
	public void setTick(ChartAxisXTick tick) {
		this.tick = tick;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public ChartAxisLabel getLabel() {
		return label;
	}
	public void setLabel(ChartAxisLabel label) {
		this.label = label;
	}	
}
