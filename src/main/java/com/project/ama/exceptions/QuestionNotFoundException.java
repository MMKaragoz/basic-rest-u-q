package com.project.ama.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public QuestionNotFoundException() {
		super();
	}

	public QuestionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QuestionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuestionNotFoundException(String message) {
		super(message);
	}

	public QuestionNotFoundException(Throwable cause) {
		super(cause);
	}

	
}
