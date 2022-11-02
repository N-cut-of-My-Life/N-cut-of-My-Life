package com.ssafy.mylifencut.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("카카오 로그인 - 카카오 엑세스 토큰 오류")
	public void getKakaoAccessTokenFail() {
		// given
		final String token = "asdf";

		// when
		final InvalidAccessTokenException result = assertThrows(InvalidAccessTokenException.class, () ->
			userService.getAccessToken(token));

		// then
		assertEquals(result.getMessage(), UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}
}
