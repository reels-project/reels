package com.github.reels_project.reels.faces.model.datatable;

public class OptionColumnSettings {
	public OptionColumnSettings() {
		this.editable = false;
		this.deletable = false;
		this.checkable = false;
	}
	
	public OptionColumnSettings(Boolean editable, Boolean deletable, Boolean checkable) {
		this.editable = editable;
		this.deletable = deletable;
		this.checkable = checkable;
	}

	private Boolean editable;
	private Boolean deletable;
	private Boolean checkable;
	
	public Boolean getEditable() {
		return editable;
	}
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}
	public Boolean getDeletable() {
		return deletable;
	}
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}
	public Boolean getCheckable() {
		return checkable;
	}
	public void setCheckable(Boolean checkable) {
		this.checkable = checkable;
	}
}
