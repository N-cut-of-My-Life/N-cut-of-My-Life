package com.ssafy.mylifencut.common.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.common.exception.BadRequestException;
import com.ssafy.mylifencut.user.exception.InvalidAccessTokenException;

@RestControllerAdvice
public class ExceptionAdvice {
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<BaseResponse> badRequestException(BadRequestException exception) {
		return new ResponseEntity<>(
			BaseResponse.from(false, exception.getMessage()),
			HttpStatus.BAD_REQUEST
		);
	}

	@ExceptionHandler(InvalidAccessTokenException.class)
	public ResponseEntity<BaseResponse> noAccessTokenException(InvalidAccessTokenException exception) {
		return new ResponseEntity<>(
			BaseResponse.from(false, exception.getMessage()),
			HttpStatus.FORBIDDEN
		);
	}
}
