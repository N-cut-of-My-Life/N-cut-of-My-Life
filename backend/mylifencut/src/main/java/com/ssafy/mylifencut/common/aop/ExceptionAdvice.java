package com.ssafy.mylifencut.common.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;

@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(InvalidStateException.class)
	public ResponseEntity<?> invalidStateException(InvalidStateException exception) {
		return new ResponseEntity<>(
			BaseResponse.from(false, exception.getMessage()),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AlreadyLikeException.class)
	public ResponseEntity<?> alreadyLikeException(AlreadyLikeException exception) {
		return new ResponseEntity<>(
			BaseResponse.from(false, exception.getMessage()),
			HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotExistLikeException.class)
	public ResponseEntity<?> notExistLikeException(NotExistLikeException exception) {
		return new ResponseEntity<>(
			BaseResponse.from(false, exception.getMessage()),
			HttpStatus.BAD_REQUEST);
	}
}
