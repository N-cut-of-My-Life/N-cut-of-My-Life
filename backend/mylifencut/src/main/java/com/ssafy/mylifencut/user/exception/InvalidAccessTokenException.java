package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class InvalidAccessTokenException extends RuntimeException {
	public InvalidAccessTokenException() {
		super(INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
