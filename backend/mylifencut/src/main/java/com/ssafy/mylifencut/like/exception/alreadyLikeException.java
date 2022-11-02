package com.ssafy.mylifencut.like.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class alreadyLikeException extends RuntimeException {
	private final LikeErrorResult errorResult;
}
