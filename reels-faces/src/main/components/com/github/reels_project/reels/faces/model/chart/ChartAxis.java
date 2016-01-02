package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxis implements Serializable{
	public ChartAxis(){
		this.rotated = false;
		this.x = new ChartAxisX();
		this.y = new ChartAxisY();
		this.y2 = null;
	}
	
	private Boolean rotated;
	private ChartAxisX x;
	private ChartAxisY y;
	private ChartAxisY y2;
	
	public Boolean getRotated() {
		return rotated;
	}
	public void setRotated(Boolean rotated) {
		this.rotated = rotated;
	}
	public ChartAxisX getX() {
		return x;
	}
	public void setX(ChartAxisX x) {
		this.x = x;
	}
	public ChartAxisY getY() {
		return y;
	}
	public void setY(ChartAxisY y) {
		this.y = y;
	}
	public ChartAxisY getY2() {
		return y2;
	}
	public void setY2(ChartAxisY y2) {
		this.y2 = y2;
	}
}
