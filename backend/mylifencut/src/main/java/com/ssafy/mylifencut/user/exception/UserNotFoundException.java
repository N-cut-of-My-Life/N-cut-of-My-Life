package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND_ERROR_MESSAGE);
	}
}
