package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

import com.ssafy.mylifencut.common.exception.BadRequestException;

public class UserNotFoundException extends BadRequestException {
	public UserNotFoundException() {
		super(USER_NOT_FOUND_ERROR_MESSAGE);
	}
}
