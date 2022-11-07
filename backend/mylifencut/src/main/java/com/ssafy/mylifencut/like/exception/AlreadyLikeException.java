package com.ssafy.mylifencut.like.exception;

import com.ssafy.mylifencut.common.exception.BadRequestException;
import com.ssafy.mylifencut.like.LikeConstant;

public class AlreadyLikeException extends BadRequestException {
	public AlreadyLikeException() {
		super(LikeConstant.ALREADY_LIKE_EXIST_ERROR_MESSAGE);
	}
}
