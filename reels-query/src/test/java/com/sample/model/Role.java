package com.sample.model;


import com.github.reels_project.reels.query.annotation.EmbeddedId;
import com.github.reels_project.reels.query.annotation.Table;


/**
 * The persistent class for the role database table.
 * 
 */
@Table(name="role")
public class Role {

	@EmbeddedId
	private RolePK id;

	private String name;

	public Role() {
	}

	public RolePK getId() {
		return this.id;
	}

	public void setId(RolePK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}