package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartPadding implements Serializable {
	public ChartPadding(){
		this.top = null;
		this.bottom = null;
		this.right = null;
		this.left = null;
	}
	
	private Double top;
	private Double bottom;
	private Double right;
	private Double left;
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
	public Double getRight() {
		return right;
	}
	public void setRight(Double right) {
		this.right = right;
	}
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
}
