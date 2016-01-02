package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartSize implements Serializable {
	public ChartSize(){
		this.width = null;
		this.height = null;
	}
	
	private Double width;
	private Double height;
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
}
