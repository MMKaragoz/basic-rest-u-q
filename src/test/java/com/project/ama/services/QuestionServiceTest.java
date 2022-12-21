package com.project.ama.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.project.ama.dto.GetQuestionResponse;
import com.project.ama.dto.GetUserResponse;
import com.project.ama.dto.converter.QuestionDtoConverter;
import com.project.ama.entities.Question;
import com.project.ama.entities.User;
import com.project.ama.exceptions.QuestionNotFoundException;
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
	
	
	
	@Test
	public void testFindOneQuestionById_whenQuestionIdExists_shouldReturnQuestion() {
		User byWhoUser = new User(0L, "name", "password");
		User toWhomUser = new User(1L, "toWhom", "pass");
		String text = "question";
		Date createDate = new Date();
		
		Question actual = new Question(byWhoUser, toWhomUser, text, createDate);
		
		Mockito.when(questionRepository.findById(actual.getId())).thenReturn(Optional.of(actual));
		
		Question expected = service.findOneQuestionById(actual.getId());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindOneQuestionById_whenQuestionIdExists_shouldThrowQuestionNotFoundException() {
		
		Mockito.when(questionRepository.findById(0L)).thenReturn(Optional.empty());
		
		assertThrows(QuestionNotFoundException.class, () -> service.findOneQuestionById(0L));
	}
	
	@Test
	public void testGetOneQuestionById_WhenQuestionIdExists_shouldReturnGetQuestionResponse() {
		User byWho = new User(0L, "name", "password");
		GetUserResponse byWhoUser = new GetUserResponse(byWho);
		
		User toWhom = new User(1L, "toWhom", "pass");
		GetUserResponse toWhomUser = new GetUserResponse(toWhom);
		
		String text = "question";
		Date createDate = new Date();
		
		Question question = new Question(byWho, toWhom, text, createDate);
		GetQuestionResponse actual = new GetQuestionResponse(
				question.getId(),
				byWhoUser, 
				toWhomUser, 
				text, 
				createDate);
		
		Mockito.when(questionRepository.findById(question.getId())).thenReturn(Optional.of(question));
		Mockito.when(converter.convertToQuestionDtoWhenGetRequest(question)).thenReturn(actual);
		
		GetQuestionResponse expected = service.getOneQuestionById(question.getId());
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetOneQuestionById_WhenQuestionIdExists_shouldThrowQuestionNotFoundException() {
		Mockito.when(questionRepository.findById(0L)).thenReturn(Optional.empty());
		
		assertThrows(QuestionNotFoundException.class, () -> service.getOneQuestionById(0L));
		
		Mockito.verifyNoInteractions(converter);
	}
}
