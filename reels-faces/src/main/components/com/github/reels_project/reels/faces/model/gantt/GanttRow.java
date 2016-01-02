package com.github.reels_project.reels.faces.model.gantt;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GanttRow {
	
	public GanttRow(){
		this.tasks = Lists.newArrayList();
		this.color = "#FFF";
	}
	
	private String name;
	private String color;

	private List<GanttTask> tasks;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public List<GanttTask> getTasks() {
		return tasks;
	}
	public void setTasks(List<GanttTask> tasks) {
		this.tasks = tasks;
	}
}
