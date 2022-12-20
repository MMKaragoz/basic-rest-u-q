package com.project.ama.dto;

import java.util.Set;

import com.project.ama.entities.Question;
import com.project.ama.entities.User;

public class GetUserResponse {

	private Long id;
	private String userName;
	
	public GetUserResponse(User userEntity) {
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
