package com.ssafy.mylifencut.common.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.mylifencut.answer.exception.InvalidStateException;

@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(InvalidStateException.class)
	public ResponseEntity<String> invalidStateException(InvalidStateException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
