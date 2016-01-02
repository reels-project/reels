package com.github.reels_project.reels.faces.model.datatable;

public class FixedSettings {
	public FixedSettings() {
		this.fixableRow = false;
		this.foxableColumn = false;
	}
	
	public FixedSettings(Boolean fixableRow, Boolean foxableColumn) {
		this.fixableRow = fixableRow;
		this.foxableColumn = foxableColumn;
	}

	private Boolean fixableRow;
	private Boolean foxableColumn;
	
	public Boolean getFixableRow() {
		return fixableRow;
	}
	public void setFixableRow(Boolean fixableRow) {
		this.fixableRow = fixableRow;
	}
	public Boolean getFoxableColumn() {
		return foxableColumn;
	}
	public void setFoxableColumn(Boolean foxableColumn) {
		this.foxableColumn = foxableColumn;
	}
}
