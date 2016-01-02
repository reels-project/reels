package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartBar implements Serializable {
	public ChartBar(){
		this.width = new ChartBarWidth();
	}
	
	private ChartBarWidth width;

	public ChartBarWidth getWidth() {
		return width;
	}
	public void setWidth(ChartBarWidth width) {
		this.width = width;
	}
	
	public class ChartBarWidth{
		public ChartBarWidth(){
		}
		
		private Double ratio;

		public Double getRatio() {
			return ratio;
		}
		public void setRatio(Double ratio) {
			this.ratio = ratio;
		}
	}
}
