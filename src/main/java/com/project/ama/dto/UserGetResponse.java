package com.project.ama.dto;

import com.project.ama.entities.User;

public class UserGetResponse {

	Long id;
	String userName;
	
	public UserGetResponse(User userEntity) {
		this.id = userEntity.getId();
		this.userName = userEntity.getUserName();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
