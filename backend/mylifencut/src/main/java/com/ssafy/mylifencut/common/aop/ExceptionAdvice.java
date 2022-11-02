package com.ssafy.mylifencut.common.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;

@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(InvalidStateException.class)
	public ResponseEntity<String> invalidStateException(InvalidStateException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AlreadyLikeException.class)
	public ResponseEntity<String> alreadyLikeException(AlreadyLikeException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
