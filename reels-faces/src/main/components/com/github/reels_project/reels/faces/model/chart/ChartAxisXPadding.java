package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisXPadding implements Serializable {
	public ChartAxisXPadding(){
		this.left = null;
		this.right = null;
	}
	
	private Double left;
	private Double right;
	
	public Double getLeft() {
		return left;
	}
	public void setLeft(Double left) {
		this.left = left;
	}
	public Double getRight() {
		return right;
	}
	public void setRight(Double right) {
		this.right = right;
	}
}
