package com.github.reels_project.reels.faces.model.datatable;

public class DatatableSettings {
	
	public DatatableSettings() {
		super();
		this.fixed = new FixedSettings();
		this.dragdrop = new DragdropSettings();
		this.optionColumn = new OptionColumnSettings();
	}

	public DatatableSettings(FixedSettings fixed, DragdropSettings dragdrop, OptionColumnSettings optionColumn) {
		this.fixed = fixed;
		this.dragdrop = dragdrop;
		this.optionColumn = optionColumn;
	}

	private FixedSettings fixed;
	private DragdropSettings dragdrop;
	private OptionColumnSettings optionColumn;
	
	public FixedSettings getFixed() {
		return fixed;
	}
	public void setFixed(FixedSettings fixed) {
		this.fixed = fixed;
	}
	public DragdropSettings getDragdrop() {
		return dragdrop;
	}
	public void setDragdrop(DragdropSettings dragdrop) {
		this.dragdrop = dragdrop;
	}
	public OptionColumnSettings getOptionColumn() {
		return optionColumn;
	}
	public void setOptionColumn(OptionColumnSettings optionColumn) {
		this.optionColumn = optionColumn;
	}
}
