package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.time.temporal.ValueRange;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartZoom implements Serializable {
	public ChartZoom(){
		this.enabled = false;
		this.rescale = false;
//		this.extent = ValueRange.of(0, 0);
	}
	
	private Boolean enabled;
	private Boolean rescale;
//	private ValueRange extent;
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Boolean getRescale() {
		return rescale;
	}
	public void setRescale(Boolean rescale) {
		this.rescale = rescale;
	}
//	public ValueRange getExtent() {
//		return extent;
//	}
//	public void setExtent(ValueRange extent) {
//		this.extent = extent;
//	}
}
