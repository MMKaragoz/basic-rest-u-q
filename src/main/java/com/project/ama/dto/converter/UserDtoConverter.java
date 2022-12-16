package com.project.ama.dto.converter;

import org.springframework.stereotype.Component;

import com.project.ama.dto.UserGetResponse;
import com.project.ama.entities.User;

@Component
public class UserDtoConverter {
	public UserGetResponse convertToUserDtoWhenGetResponse(User from) {
		return new UserGetResponse(
				from);
	}
}
