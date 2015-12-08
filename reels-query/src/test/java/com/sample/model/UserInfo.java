package com.sample.model;


import com.github.reels_project.reels.query.annotation.Id;
import com.github.reels_project.reels.query.annotation.Table;

/**
 * Entity implementation class for Entity: UserInfo
 *
 */
@Table
public class UserInfo {
	
	@Id
	private String userId;

	public UserInfo() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
