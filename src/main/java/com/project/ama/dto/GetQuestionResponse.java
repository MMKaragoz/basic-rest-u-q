package com.project.ama.dto;

import java.util.Date;

public class GetQuestionResponse {

	private Long id;
	private GetUserResponse byWho;
	private GetUserResponse toWhom;
	private String text;
	private Date createDate;
	
	public GetQuestionResponse(Long id, GetUserResponse byWho, GetUserResponse toWhom, String text, Date createDate) {
		this.id = id;
		this.byWho = byWho;
		this.toWhom = toWhom;
		this.text = text;
		this.createDate = createDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GetUserResponse getByWho() {
		return byWho;
	}

	public void setByWho(GetUserResponse byWho) {
		this.byWho = byWho;
	}

	public GetUserResponse getToWhom() {
		return toWhom;
	}

	public void setToWhom(GetUserResponse toWhom) {
		this.toWhom = toWhom;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
}
