package com.sample.model;


import com.github.reels_project.reels.query.annotation.Column;
import com.github.reels_project.reels.query.annotation.Id;
import com.github.reels_project.reels.query.annotation.Table;


/**
 * The persistent class for the unit database table.
 * 
 */
@Table(name="unit")
public class Unit {

	@Id
	@Column(name="unit_id")
	private String unitId;

	private String name;


	public Unit() {
	}

	public String getUnitId() {
		return this.unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}