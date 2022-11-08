package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class NotValidTokenException extends RuntimeException {
	public NotValidTokenException() {
		super(INVALID_REFRESH_TOKEN_ERROR_MESSAGE);
	}
}
