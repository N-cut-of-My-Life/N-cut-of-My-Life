package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

import com.ssafy.mylifencut.common.exception.BadRequestException;

public class InvalidRefreshTokenException extends BadRequestException {
	public InvalidRefreshTokenException() {
		super(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);
	}
}
