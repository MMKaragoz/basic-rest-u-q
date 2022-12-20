package com.project.ama.dto.converter;

import org.springframework.stereotype.Component;

import com.project.ama.dto.CreateQuestionRequest;
import com.project.ama.dto.GetQuestionResponse;
import com.project.ama.entities.Question;

@Component
public class QuestionDtoConverter {
	
	private final UserDtoConverter userDtoConverter;
	
	public QuestionDtoConverter(UserDtoConverter userDtoConverter) {
		this.userDtoConverter = userDtoConverter;
	}
	
	public CreateQuestionRequest convertToQuestionDtoWhenCreateRequest(Question from) {
		return new CreateQuestionRequest(
				from.getbyWhoUser().getId(),
				from.gettoWhomUser().getId(),
				from.getText()
				);
	}
	
	public GetQuestionResponse convertToQuestionDtoWhenGetRequest(Question from) {
		return new GetQuestionResponse(
				from.getId(),
				userDtoConverter.convertToUserDtoWhenGetResponse(from.getbyWhoUser()), 
				userDtoConverter.convertToUserDtoWhenGetResponse(from.gettoWhomUser()), 
				from.getText(), 
				from.getCreateDate());
	}
}
