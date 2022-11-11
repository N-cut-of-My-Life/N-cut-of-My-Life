package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class NoAccessTokenException extends RuntimeException {
	public NoAccessTokenException() {
		super(NO_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
