package com.github.reels_project.reels.faces.model.datatable;

public class DragdropSettings {
	public DragdropSettings() {
		this.draggableRow = false;
	}
	
	public DragdropSettings(Boolean draggableRow) {
		this.draggableRow = draggableRow;
	}

	private Boolean draggableRow;

	public Boolean getDraggableRow() {
		return draggableRow;
	}
	public void setDraggableRow(Boolean draggableRow) {
		this.draggableRow = draggableRow;
	}
}
