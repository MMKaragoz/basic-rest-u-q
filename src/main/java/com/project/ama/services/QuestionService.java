package com.project.ama.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ama.dto.CreateQuestionRequest;
import com.project.ama.dto.GetQuestionResponse;
import com.project.ama.dto.UpdateQuestionRequest;
import com.project.ama.dto.converter.QuestionDtoConverter;
import com.project.ama.entities.Question;
import com.project.ama.entities.User;
import com.project.ama.exceptions.QuestionNotFoundException;
import com.project.ama.repositories.QuestionRepository;

@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	private final QuestionDtoConverter converter;
	private final UserService userService;
	
	public QuestionService(QuestionRepository questionRepository, UserService userService, QuestionDtoConverter converter) {
		this.questionRepository = questionRepository;
		this.userService = userService;
		this.converter = converter;
	}
	
	public List<GetQuestionResponse> getAllQuestions() {
		return convertToQuestionListToGetQuestionResponseList(questionRepository.findAll());
	}
	
	public Question saveOneQuestion(Question newQuestion) {
		return questionRepository.save(newQuestion);
	}
	
	protected Question findOneQuestionById(Long questionId) {
		return questionRepository.findById(questionId).orElseThrow(
				() -> new QuestionNotFoundException("Question could not find by id:" + questionId) );
	}

	public Question createOneQuestion(CreateQuestionRequest request) {
		
		User byWhoUser = userService.findOneUserById(request.getByWhoUserId());
		User toWhomUser = userService.findOneUserById(request.getToWhomUserId());
		Question toSave = new Question(
									   byWhoUser,
									   toWhomUser,
									   request.getText(),
									   new Date());
		return saveOneQuestion(toSave);
	}

	public List<GetQuestionResponse> getAllQuestionsDependOnToWhomUserId(Long toWhomUserId) {
		return convertToQuestionListToGetQuestionResponseList(questionRepository.findByToWhomUserId(toWhomUserId));
	}
	
	public GetQuestionResponse getOneQuestionById(Long questionId) {
		return converter.convertToQuestionDtoWhenGetRequest(findOneQuestionById(questionId));
	}
	
	public ResponseEntity<Void> updateOneQuestionById(Long questionId, UpdateQuestionRequest request) {
		Question toUpdate = findOneQuestionById(questionId);
		toUpdate.setText(request.getText());	
		saveOneQuestion(toUpdate);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public void deleteOneQuestionById(Long questionId) {
		questionRepository.deleteById(questionId);
	}
	
	private List<GetQuestionResponse> convertToQuestionListToGetQuestionResponseList(List<Question> questions) {	
		return questions.stream().map(question -> converter.convertToQuestionDtoWhenGetRequest(question)).collect(Collectors.toList());
	}

	
}
