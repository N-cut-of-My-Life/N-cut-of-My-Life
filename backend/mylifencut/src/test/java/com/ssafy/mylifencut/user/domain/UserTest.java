package com.ssafy.mylifencut.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[유저 도메인]")
class UserTest {

	@Test
	@DisplayName("[성공] - 비밀번호 받기")
	void getPassword() {
		// given
		final User user = User.builder().build();

		// when
		String result = user.getPassword();

		// then
		assertNull(result);
	}

}