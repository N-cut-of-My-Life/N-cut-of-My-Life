package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class ExpiredTokenException extends RuntimeException {
	public ExpiredTokenException() {
		super(EXPIRED_REFRESH_TOKEN_ERROR_MESSAGE);
	}
}
