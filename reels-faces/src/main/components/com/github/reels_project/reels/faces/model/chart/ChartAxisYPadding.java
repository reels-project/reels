package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisYPadding implements Serializable {
	public ChartAxisYPadding(){
		this.top = null;
		this.bottom = null;
	}
	
	private Double top;
	private Double bottom;
	
	public Double getTop() {
		return top;
	}
	public void setTop(Double top) {
		this.top = top;
	}
	public Double getBottom() {
		return bottom;
	}
	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}
}
