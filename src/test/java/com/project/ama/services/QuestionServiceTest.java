package com.project.ama.services;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;

import com.project.ama.dto.converter.QuestionDtoConverter;
import com.project.ama.repositories.QuestionRepository;

public class QuestionServiceTest {

	private QuestionService service;
	private QuestionRepository questionRepository;
	private QuestionDtoConverter converter;
	
	private UserService userService;
	
	@BeforeEach
	public void setUp() {
		questionRepository = mock(QuestionRepository.class);
		converter = mock(QuestionDtoConverter.class);
		
		userService = mock(UserService.class);
		
		service = new QuestionService(questionRepository, userService, converter);
	}
	
	
}
