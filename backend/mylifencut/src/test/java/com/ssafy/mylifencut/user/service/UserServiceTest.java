package com.ssafy.mylifencut.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.dto.UserInfo;
import com.ssafy.mylifencut.user.exception.InvalidAccessTokenException;
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
		final String token = "INVALID_TOKEN";

		// when
		final InvalidAccessTokenException result = assertThrows(InvalidAccessTokenException.class, () ->
			userService.getAccessToken(token));

		// then
		assertEquals(result.getMessage(), UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("카카오 로그인 - 사용자 정보 받아오기 실패")
	public void getUserInfoFromKakaoFail() {
		// given
		final String token = "INVALID_TOKEN";

		// when
		final InvalidAccessTokenException result = assertThrows(InvalidAccessTokenException.class, () ->
			userService.getUserInfo(token));

		//then
		assertEquals(result.getMessage(), UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("카카오 로그인 - 사용자 정보로 신규유저 여부 확인(신규유저)")
	public void isNewUser() {
		// given
		final UserInfo userInfo = UserInfo.builder()
			.email("ssafy@emali.com")
			.name("홍길동")
			.build();
		doReturn(false).when(userRepository).findByEmail(userInfo.getEmail());

		// when
		final boolean result = userService.isNewUser(userInfo);

		// then
		assertFalse(result);
	}
}
