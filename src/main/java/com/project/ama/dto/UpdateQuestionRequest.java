package com.project.ama.dto;

public class UpdateQuestionRequest {

	private String text;

	public UpdateQuestionRequest() {
		
	}
	
	public UpdateQuestionRequest(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
