package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartPie implements Serializable {
	public ChartPie(){
		this.label = new ChartPieLabel();
		this.expand = true;
	}
	
	private ChartPieLabel label;
	private Boolean expand;
	
	public ChartPieLabel getLabel() {
		return label;
	}
	public void setLabel(ChartPieLabel label) {
		this.label = label;
	}
	public Boolean getExpand() {
		return expand;
	}
	public void setExpand(Boolean expand) {
		this.expand = expand;
	}

	public class ChartPieLabel{
		public ChartPieLabel(){
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
