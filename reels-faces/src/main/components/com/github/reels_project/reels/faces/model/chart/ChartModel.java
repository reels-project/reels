package com.github.reels_project.reels.faces.model.chart;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ChartModel implements Serializable{
	public ChartModel(){
		this.chartId = "chart";
		this.type = ChartType.LINE;
		this.series = Lists.newArrayList();
		this.size = new ChartSize();
		this.padding = new ChartPadding();
		this.order = ChartOrder.DESC;
		this.axis = new ChartAxis();
		this.point = new ChartPoint();
		this.bar = new ChartBar();
		this.donut = new ChartDonut();
		this.pie = new ChartPie();
		this.zoom = new ChartZoom();
	}
	
	private String chartId;
	private String type;
	private List<ChartSeries> series;
	private ChartSize size;
	private ChartPadding padding;
	private String order;
	private ChartAxis axis;
	private ChartPoint point;
	private ChartBar bar;
	private ChartDonut donut;
	private ChartPie pie;
	
	private ChartZoom zoom;
	
	public String getChartId() {
		return chartId;
	}
	public void setChartId(String chartId) {
		this.chartId = chartId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ChartSeries> getSeries() {
		return series;
	}
	public void setSeries(List<ChartSeries> series) {
		this.series = series;
	}
	public void addSeries(ChartSeries series) {
		this.series.add(series);
	}
	public void removeSeries(ChartSeries series) {
		this.series.remove(series);
	}
	public void removeSeries(Integer index) {
		this.series.remove(index);
	}
	public ChartSize getSize() {
		return size;
	}
	public void setSize(ChartSize size) {
		this.size = size;
	}
	public ChartPadding getPadding() {
		return padding;
	}
	public void setPadding(ChartPadding padding) {
		this.padding = padding;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public ChartAxis getAxis() {
		return axis;
	}
	public void setAxis(ChartAxis axis) {
		this.axis = axis;
	}
	public ChartPoint getPoint() {
		return point;
	}
	public void setPoint(ChartPoint point) {
		this.point = point;
	}
	public ChartBar getBar() {
		return bar;
	}
	public void setBar(ChartBar bar) {
		this.bar = bar;
	}
	public ChartDonut getDonut() {
		return donut;
	}
	public void setDonut(ChartDonut donut) {
		this.donut = donut;
	}
	public ChartPie getPie() {
		return pie;
	}
	public void setPie(ChartPie pie) {
		this.pie = pie;
	}
	public ChartZoom getZoom() {
		return zoom;
	}
	public void setZoom(ChartZoom zoom) {
		this.zoom = zoom;
	}
	

	
	
//	@Override
//	public void serialize(ChartModel value, JsonGenerator jgen,
//	        SerializerProvider provider) throws IOException,
//	        JsonProcessingException {
//	    jgen.writeStartObject();
//	    jgen.writeNumberField("id", value.id);
//	    jgen.writeNumberField("itemNr", value.itemNr);
//	    jgen.writeNumberField("createdBy", value.user.id);
//	    jgen.writeEndObject();
//	}
}
