package com.ssafy.mylifencut.like.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LikeErrorResult {

	ALREADY_LIKE(HttpStatus.BAD_REQUEST, "이미 좋아요가 눌린 게시물입니다.");

	private final HttpStatus httpStatus;
	private final String message;
}
