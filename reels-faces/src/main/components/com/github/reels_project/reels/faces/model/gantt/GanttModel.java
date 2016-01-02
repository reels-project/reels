package com.github.reels_project.reels.faces.model.gantt;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GanttModel implements Serializable{
	private List<GanttRow> rows;

	public List<GanttRow> getRows() {
		return rows;
	}

	public void setRows(List<GanttRow> rows) {
		this.rows = rows;
	}
}
