package com.ssafy.mylifencut.user.exception;

import static com.ssafy.mylifencut.user.UserConstant.*;

public class InvalidKakaoAccessTokenException extends RuntimeException {
	public InvalidKakaoAccessTokenException() {
		super(INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
