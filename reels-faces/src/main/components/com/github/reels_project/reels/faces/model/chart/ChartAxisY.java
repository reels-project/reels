package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartAxisY implements Serializable {
	public ChartAxisY(){
		this.show = true;
		this.inner = false;
		this.max = null;
		this.min = null;
		this.inverted = false;
		this.label = new ChartAxisLabel();
		this.tick = new ChartAxisYTick();
		this.padding = new ChartAxisYPadding();
	}
	
	private Boolean show;
	private Boolean inner;
	private Double max;
	private Double min;
	private Boolean inverted;
	private ChartAxisLabel label;
	private ChartAxisYTick tick;
	private ChartAxisYPadding padding;
	
	public Boolean getShow() {
		return show;
	}
	public void setShow(Boolean show) {
		this.show = show;
	}
	public Boolean getInner() {
		return inner;
	}
	public void setInner(Boolean inner) {
		this.inner = inner;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Boolean getInverted() {
		return inverted;
	}
	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}
	public ChartAxisLabel getLabel() {
		return label;
	}
	public void setLabel(ChartAxisLabel label) {
		this.label = label;
	}
	public ChartAxisYTick getTick() {
		return tick;
	}
	public void setTick(ChartAxisYTick tick) {
		this.tick = tick;
	}
	public ChartAxisYPadding getPadding() {
		return padding;
	}
	public void setPadding(ChartAxisYPadding padding) {
		this.padding = padding;
	}
}
