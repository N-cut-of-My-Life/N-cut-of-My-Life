package com.ssafy.mylifencut.article.exception;

public class NotFoundUserException extends RuntimeException {

	public NotFoundUserException(String message) {
		super(message);
	}
}
