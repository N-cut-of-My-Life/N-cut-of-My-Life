package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class InvalidRefreshTokenException extends RuntimeException {
	public InvalidRefreshTokenException() {
		super(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);
	}
}
