package com.ssafy.mylifencut.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.dto.UserInfo;
import com.ssafy.mylifencut.user.exception.InvalidAccessTokenException;
import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;

	private final String email = "ssafy@email.com";
	private final String name = "홍길동";

	@Test
	@DisplayName("카카오 로그인 - 카카오 엑세스 토큰 오류")
	public void getKakaoAccessTokenFail() {
		// given
		final String token = "INVALID_TOKEN";

		// when
		final InvalidAccessTokenException result = assertThrows(InvalidAccessTokenException.class,
			() -> userService.getAccessToken(token));

		// then
		assertEquals(result.getMessage(), UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("카카오 로그인 - 사용자 정보 받아오기 실패")
	public void getUserInfoFromKakaoFail() {
		// given
		final String token = "INVALID_TOKEN";

		// when
		final InvalidAccessTokenException result = assertThrows(InvalidAccessTokenException.class,
			() -> userService.getUserInfo(token));

		//then
		assertEquals(result.getMessage(), UserConstant.INVALID_ACCESS_TOKEN_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("카카오 로그인 - 사용자 정보로 신규유저 여부 확인(신규유저)")
	public void isNewUser() {
		// given
		final UserInfo userInfo = newUserInfo();
		doReturn(Optional.empty()).when(userRepository).findByEmail(userInfo.getEmail());

		// when
		final boolean result = userService.isNewUser(userInfo);

		// then
		assertFalse(result);
	}

	@Test
	@DisplayName("카카오 로그인 - 신규유저 유저 회원가입")
	public void loginNewUser() {
		// given
		final UserInfo userInfo = newUserInfo();
		final User user = User.builder()
			.id(1)
			.email(userInfo.getEmail())
			.name(userInfo.getName())
			.build();
		doReturn(user).when(userRepository).save(any(User.class));

		// when
		final User result = userService.join(userInfo);

		//then
		assertEquals(user.getId(), result.getId());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getName(), result.getName());
	}

	@Test
	@DisplayName("카카오 로그인 - 사용자 정보로 신규유저 여부 확인(기존유저)")
	public void isExistingUser() {
		// given
		final UserInfo userInfo = newUserInfo();
		final User user = User.builder().build();
		doReturn(Optional.of(user)).when(userRepository).findByEmail(userInfo.getEmail());

		// when
		final boolean result = userService.isNewUser(userInfo);

		// then
		assertTrue(result);
	}

	@Test
	@DisplayName("카카오 로그인 - 로그인")
	public void loginExistingUser() {
		// given
		final UserInfo userInfo = newUserInfo();
		final User user = User.builder()
			.id(1)
			.email(email)
			.name(name)
			.build();
		doReturn(Optional.of(user)).when(userRepository).findByEmail(email);

		// when
		Optional<User> result = userService.login(userInfo);

		// then
		assertTrue(result.isPresent());
		assertEquals(user.getId(), result.get().getId());
		assertEquals(user.getEmail(), result.get().getEmail());
		assertEquals(user.getName(), result.get().getName());

	}

	public UserInfo newUserInfo() {
		return UserInfo.builder()
			.email(email)
			.name(name)
			.build();
	}
}
