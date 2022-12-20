package com.project.ama.dto;

public class CreateQuestionRequest {
	
	Long byWhoUserId;
	Long toWhomUserId;
	String text;
	
	public CreateQuestionRequest() {
		
	}
	
	public CreateQuestionRequest(Long byWhoUserId, Long toWhomUserId, String text) {
		this.byWhoUserId = byWhoUserId;
		this.toWhomUserId = toWhomUserId;
		this.text = text;
	}
	
	public Long getByWhoUserId() {
		return byWhoUserId;
	}
	
	public void setByWhoUserId(Long byWhoUserId) {
		this.byWhoUserId = byWhoUserId;
	}
	
	public Long getToWhomUserId() {
		return toWhomUserId;
	}
	
	public void setToWhomUserId(Long toWhomUserId) {
		this.toWhomUserId = toWhomUserId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "CreateQuestionRequest [byWhoUserId=" + byWhoUserId + ", toWhomUserId=" + toWhomUserId + ", text=" + text
				+ "]";
	}
	
	
}
