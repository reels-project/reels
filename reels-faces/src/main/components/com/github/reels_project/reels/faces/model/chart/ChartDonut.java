package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartDonut implements Serializable {
	public ChartDonut(){
		this.label = new ChartDonutLabel();
		this.expand = true;
		this.width = null;
		this.title = null;
	}
	
	private ChartDonutLabel label;
	private Boolean expand;
	private Double width;
	private String title;
	
	public ChartDonutLabel getLabel() {
		return label;
	}
	public void setLabel(ChartDonutLabel label) {
		this.label = label;
	}
	public Boolean getExpand() {
		return expand;
	}
	public void setExpand(Boolean expand) {
		this.expand = expand;
	}
	public Double getWidth() {
		return width;
	}
	public void setWidth(Double width) {
		this.width = width;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public class ChartDonutLabel{
		public ChartDonutLabel(){
			this.show = true;
			this.threshold = 0.05;
		}
		
		private Boolean show;
		private Double threshold;
		public Boolean getShow() {
			return show;
		}
		public void setShow(Boolean show) {
			this.show = show;
		}
		public Double getThreshold() {
			return threshold;
		}
		public void setThreshold(Double threshold) {
			this.threshold = threshold;
		}
	}
}
