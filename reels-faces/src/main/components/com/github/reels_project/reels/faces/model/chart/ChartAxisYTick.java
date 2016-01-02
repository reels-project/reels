package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisYTick implements Serializable {
	public ChartAxisYTick(){
		this.outer = null;
		this.values = null;
		this.count = null;
	}
	
	private Boolean outer;
	private List<Object> values;
	private Integer count;
	
	public Boolean getOuter() {
		return outer;
	}
	public void setOuter(Boolean outer) {
		this.outer = outer;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
}
