package com.github.reels_project.reels.faces.model.datatable;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jersey.repackaged.com.google.common.collect.Lists;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DatatableModel implements Serializable{
	
	public DatatableModel(){
		this.rows = Lists.newArrayList();
		this.columns = Lists.newArrayList();
		this.fixedRows = Lists.newArrayList();
		this.fixedColumns = Lists.newArrayList();
		this.settings = new DatatableSettings();
	}
	
	private List<Object> rows;
	private List<DatatableColumn> columns;
	private List<Object> fixedRows;
	private List<DatatableColumn> fixedColumns;
	private DatatableSettings settings;
	
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	public void addRow(Object row){
		this.rows.add(row);
	}
	public void removeRow(Object row){
		this.rows.remove(row);
	}
	public List<DatatableColumn> getColumns() {
		return columns;
	}
	public void setColumns(List<DatatableColumn> columns) {
		this.columns = columns;
	}
	
	public List<Object> getFixedRows() {
		return fixedRows;
	}
	public void setFixedRows(List<Object> fixedRows) {
		this.fixedRows = fixedRows;
	}
	public void addFixedRow(Object row){
		this.fixedRows.add(row);
	}
	public void removeFixedRow(Object row){
		this.fixedRows.remove(row);
	}
	public List<DatatableColumn> getFixedColumns() {
		return fixedColumns;
	}
	public void setFixedColumns(List<DatatableColumn> fixedColumns) {
		this.fixedColumns = fixedColumns;
	}
	public DatatableSettings getSettings() {
		return settings;
	}
	public void setSettings(DatatableSettings settings) {
		this.settings = settings;
	}
	
}
