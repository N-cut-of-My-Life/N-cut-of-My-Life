package com.ssafy.mylifencut.like.exception;

import com.ssafy.mylifencut.like.LikeConstant;

public class NotExistLikeException extends RuntimeException {
	public NotExistLikeException() {
		super(LikeConstant.NOT_EXIST_LIKE_ERROR_MESSAGE);
	}
}
