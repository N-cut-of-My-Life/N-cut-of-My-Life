package com.ssafy.mylifencut.user.service;

import static com.ssafy.mylifencut.user.UserConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.user.JwtTokenProvider;
import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.dto.TokenRequest;
import com.ssafy.mylifencut.user.dto.TokenResponse;
import com.ssafy.mylifencut.user.dto.UserInfo;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.exception.InvalidRefreshTokenException;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private JwtTokenProvider jwtTokenProvider;

	private final String email = "ssafy@email.com";
	private final String name = "홍길동";

	@Nested
	@DisplayName("로그인 및 회원가입 테스트")
	class loginAndRegisterTest {
		@Test
		@DisplayName("카카오 로그인 - 카카오 엑세스 토큰 오류")
		public void getKakaoAccessTokenFail() {
			// given
			final String token = "INVALID_TOKEN";

			// when
			final InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.getAccessToken(token));

			// then
			assertEquals(result.getMessage(), UserConstant.INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("카카오 로그인 - 사용자 정보 받아오기 실패")
		public void getUserInfoFromKakaoFail() {
			// given
			final String token = "INVALID_TOKEN";

			// when
			final InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.getUserInfo(token));

			//then
			assertEquals(result.getMessage(), UserConstant.INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("카카오 로그인 - 사용자 정보로 신규유저 여부 확인(신규유저)")
		public void isNewUser() {
			// given
			final UserInfo userInfo = newUserInfo();
			doReturn(Optional.empty()).when(userRepository).findByEmail(userInfo.getEmail());

			// when
			final boolean result = userService.isExistingUser(userInfo);

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
			final boolean result = userService.isExistingUser(userInfo);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("카카오 로그인 - 로그인 실패")
		public void loginExistingUserFail() {
			// given
			final UserInfo userInfo = newUserInfo();
			doReturn(Optional.empty()).when(userRepository).findByEmail(email);

			// when
			UserNotFoundException result = assertThrows(UserNotFoundException.class,
				() -> userService.login(userInfo));

			// then
			assertEquals(result.getMessage(), USER_NOT_FOUND_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("카카오 로그인 - 기존유저 로그인")
		public void loginExistingUserSuccess() {
			// given
			final UserInfo userInfo = newUserInfo();
			final User user = User.builder()
				.id(1)
				.email(email)
				.name(name)
				.build();
			doReturn(Optional.of(user)).when(userRepository).findByEmail(email);

			// when
			User result = userService.login(userInfo);

			// then
			assertEquals(user.getId(), result.getId());
			assertEquals(user.getEmail(), result.getEmail());
			assertEquals(user.getName(), result.getName());
		}

		@Test
		@DisplayName("카카오 로그인 - 실패")
		public void kakaoLoginFail() {
			// given
			String token = "INVALID_TOKEN";

			// when
			InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.kakaoLogin(token));

			// then
			assertEquals(result.getMessage(), INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
		}
	}

	@Nested
	@DisplayName("리프레쉬 토큰 테스트")
	class refreshTokenTest {

		@Test
		@DisplayName("발급되지 않았던 리프레쉬 토큰")
		void notValidRefreshToken() {
			// given
			TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("TOKEN")
				.refreshToken("TOKEN")
				.build();
			doThrow(new InvalidRefreshTokenException())
				.when(jwtTokenProvider)
				.validateToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(tokenRequest));
		}

		@Test
		@DisplayName("만료된 리프레쉬 토큰")
		void expiredRefreshToken() {
			// given
			TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("TOKEN")
				.refreshToken("TOKEN")
				.build();
			doThrow(new InvalidRefreshTokenException())
				.when(jwtTokenProvider)
				.validateToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(tokenRequest));
		}

		@Test
		@DisplayName("올바른 리프레쉬 토큰")
		void validRefreshToken() {
			// given
			TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("TOKEN")
				.refreshToken("TOKEN")
				.build();
			TokenResponse tokenResponse = TokenResponse.builder()
				.AccessToken("TOKEN")
				.RefreshToken("TOKEN")
				.build();
			doReturn(tokenResponse)
				.when(jwtTokenProvider)
				.validateToken(any());

			// when
			TokenResponse result = userService.reissueToken(tokenRequest);

			// then
			assertEquals(tokenResponse.getRefreshToken(), result.getRefreshToken());
			assertEquals(tokenResponse.getAccessToken(), result.getAccessToken());
		}
	}

	public UserInfo newUserInfo() {
		return UserInfo.builder()
			.email(email)
			.name(name)
			.build();
	}
}
