package com.ssafy.mylifencut.like.exception;

import com.ssafy.mylifencut.common.exception.BadRequestException;
import com.ssafy.mylifencut.like.LikeConstant;

public class NotExistLikeException extends BadRequestException {
	public NotExistLikeException() {
		super(LikeConstant.NOT_EXIST_LIKE_ERROR_MESSAGE);
	}
}
