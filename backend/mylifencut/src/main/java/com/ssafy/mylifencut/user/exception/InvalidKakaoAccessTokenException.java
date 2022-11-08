package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

import com.ssafy.mylifencut.common.exception.BadRequestException;

public class InvalidKakaoAccessTokenException extends BadRequestException {
	public InvalidKakaoAccessTokenException() {
		super(INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
