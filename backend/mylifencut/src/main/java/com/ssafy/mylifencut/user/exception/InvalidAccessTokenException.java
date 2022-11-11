package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

import com.ssafy.mylifencut.common.exception.BadRequestException;

public class InvalidAccessTokenException extends BadRequestException {
	public InvalidAccessTokenException() {
		super(INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
