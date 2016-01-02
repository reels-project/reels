package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisXTick implements Serializable {
	public ChartAxisXTick(){
		this.centered = false;
		this.count = null;
		this.fit = true;
		this.values = null;
		this.rotate = 0;
		this.outer = true;
	}
	
	private Boolean centered;
	private Boolean fit;
	private Integer count;
	private List<Object> values;
	private Integer rotate;
	private Boolean outer;
	
	public Boolean getCentered() {
		return centered;
	}
	public void setCentered(Boolean centered) {
		this.centered = centered;
	}
	public Boolean getFit() {
		return fit;
	}
	public void setFit(Boolean fit) {
		this.fit = fit;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public Integer getRotate() {
		return rotate;
	}
	public void setRotate(Integer rotate) {
		this.rotate = rotate;
	}
	public Boolean getOuter() {
		return outer;
	}
	public void setOuter(Boolean outer) {
		this.outer = outer;
	}
	
}
