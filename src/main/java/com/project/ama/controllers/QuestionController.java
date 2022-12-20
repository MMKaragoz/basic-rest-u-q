package com.project.ama.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ama.dto.CreateQuestionRequest;
import com.project.ama.dto.GetQuestionResponse;
import com.project.ama.dto.UpdateQuestionRequest;
import com.project.ama.entities.Question;
import com.project.ama.services.QuestionService;

@RestController
@RequestMapping("v1/questions")
public class QuestionController {

	private final QuestionService questionService;
	
	public QuestionController(QuestionService questionService) {
		this.questionService = questionService;
	}
	
	@GetMapping
	public List<GetQuestionResponse> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@PostMapping
	public Question createOneQuestion(@RequestBody CreateQuestionRequest request) {
		return questionService.createOneQuestion(request);
	}
	
	// ----
	
	@GetMapping("/user")
	public List<GetQuestionResponse> getAllQuestionsDependOnToWhomUserId(@RequestParam Long id) {
		return questionService.getAllQuestionsDependOnToWhomUserId(id);
	}
	
	// -- "/{questionId}" --
	
	@GetMapping("/{questionId}")
	public GetQuestionResponse getOneQuestionById(@PathVariable Long questionId) {
		return questionService.getOneQuestionById(questionId);
	}
	
	@PutMapping("/{questionId}")
	public ResponseEntity<Void> updateOneQuestionById(@PathVariable Long questionId, @RequestBody UpdateQuestionRequest request) {
		return questionService.updateOneQuestionById(questionId, request);
	}
	
	@DeleteMapping("/{questionId}")
	public void deleteOneQuestionById(@PathVariable Long questionId) {
		questionService.deleteOneQuestionById(questionId);
	}
}
