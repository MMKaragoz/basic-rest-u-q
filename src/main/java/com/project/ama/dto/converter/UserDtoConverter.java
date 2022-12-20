package com.project.ama.dto.converter;

import org.springframework.stereotype.Component;

import com.project.ama.dto.GetUserResponse;
import com.project.ama.entities.User;

@Component
public class UserDtoConverter {
	public GetUserResponse convertToUserDtoWhenGetResponse(User from) {
		return new GetUserResponse(
				from);
	}
}
