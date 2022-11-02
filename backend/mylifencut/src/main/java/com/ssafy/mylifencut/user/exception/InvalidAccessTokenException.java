package com.ssafy.mylifencut.user.exception;

public class InvalidAccessTokenException extends RuntimeException {
	public InvalidAccessTokenException(String message) {
		super(message);
	}
}
