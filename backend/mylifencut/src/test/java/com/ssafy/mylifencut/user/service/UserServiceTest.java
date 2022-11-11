package com.ssafy.mylifencut.user.service;

import static com.ssafy.mylifencut.user.UserConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.mylifencut.user.JwtTokenProvider;
import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.domain.RefreshToken;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.dto.Token;
import com.ssafy.mylifencut.user.dto.UserInfo;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.exception.InvalidRefreshTokenException;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.RefreshTokenRepository;
import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유저 서비스]")
public class UserServiceTest {

	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	@Mock
	private JwtTokenProvider jwtTokenProvider;
	@Mock
	private RefreshTokenRepository refreshTokenRepository;

	private final String email = "ssafy@email.com";
	private final String name = "홍길동";

	@Nested
	@DisplayName("[카카오 정보 받기]")
	class getData {
		@Test
		@DisplayName("[실패] - 카카오 엑세스 토큰 오류")
		void getKakaoAccessTokenFail() {
			// given
			final String token = "INVALID_TOKEN";

			// when
			final InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.getAccessToken(token));

			// then
			assertEquals(UserConstant.INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[실패] - 사용자 정보 받아오기 오류")
		void getUserInfoFromKakaoFail() {
			// given
			final String token = "INVALID_TOKEN";

			// when
			final InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.getUserInfo(token));

			//then
			assertEquals(UserConstant.INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[성공] - Json 결과 가져오기")
		void getResultSuccess() throws IOException {
			// given
			final URL url = new URL("https://my-json-server.typicode.com/qulip/apitest/kakao");
			final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			final String testToken = "API_TEST_TOKEN";

			// when
			final String result = userService.getResult(conn);

			// then
			JsonElement element = JsonParser.parseString(result);
			assertEquals(testToken, element.getAsJsonObject().get("access_token").getAsString());
			assertEquals(testToken, element.getAsJsonObject().get("refresh_token").getAsString());
		}
	}

	@Nested
	@DisplayName("[로그인 및 회원가입]")
	class loginAndRegisterTest {

		@Test
		@DisplayName("[성공] - 카카오 정보으로 UserInfo 생성")
		void getUserInfoFromKakao() throws IOException {
			// given
			final URL url = new URL("https://my-json-server.typicode.com/qulip/apitest/userInfo");
			final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			final String json = userService.getResult(conn);
			final UserInfo userInfo = UserInfo.builder()
				.email("apiTest@email.com")
				.name("싸피")
				.build();

			// when
			UserInfo result = userService.getUserInfoFromKakaoProfile(json);

			// then
			assertEquals(userInfo.getEmail(), result.getEmail());
			assertEquals(userInfo.getName(), result.getName());
		}

		@Test
		@DisplayName("[성공] - 사용자 정보로 신규유저 여부 확인(신규유저)")
		void isNewUser() {
			// given
			final UserInfo userInfo = newUserInfo();
			doReturn(Optional.empty()).when(userRepository).findByEmail(userInfo.getEmail());

			// when
			final boolean result = userService.isExistingUser(userInfo);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("[성공] - 신규유저 유저 회원가입")
		void loginNewUser() {
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
		@DisplayName("[성공] - 사용자 정보로 신규유저 여부 확인(기존유저)")
		void isExistingUser() {
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
		@DisplayName("[실패] - 로그인 실패")
		void loginExistingUserFail() {
			// given
			final UserInfo userInfo = newUserInfo();
			doReturn(Optional.empty()).when(userRepository).findByEmail(email);

			// when
			UserNotFoundException result = assertThrows(UserNotFoundException.class,
				() -> userService.login(userInfo));

			// then
			assertEquals(USER_NOT_FOUND_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[성공] - 기존유저 로그인")
		void loginExistingUserSuccess() {
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
		@DisplayName("[실패] - 카카오 토큰 오류")
		void kakaoLoginFail() {
			// given
			String token = "INVALID_TOKEN";

			// when
			InvalidKakaoAccessTokenException result = assertThrows(InvalidKakaoAccessTokenException.class,
				() -> userService.kakaoLogin(token));

			// then
			assertEquals(INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE, result.getMessage());
		}
	}

	@Nested
	@DisplayName("[리프레쉬 토큰]")
	class refreshTokenTest {

		@Test
		@DisplayName("[실패] - 토큰이 null 일 경우")
		void nullRefreshToken() {
			// given
			final String refreshToken = null;

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(refreshToken));
		}

		@Test
		@DisplayName("[실패] - 발급되지 않았던 리프레쉬 토큰")
		void notValidRefreshToken() {
			// given
			final String refreshToken = "INVALID_TOKEN";
			doReturn(false)
				.when(jwtTokenProvider)
				.validateToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(refreshToken));
		}

		@Test
		@DisplayName("[실패] - 만료된 리프레쉬 토큰")
		void expiredRefreshToken() {
			// given
			final String refreshToken = "INVALID_TOKEN";
			doReturn(false)
				.when(jwtTokenProvider)
				.validateToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(refreshToken));
		}

		@Test
		@DisplayName("[실패] - 저장된 리프레쉬 토큰이 없는 경우")
		void isNotInRefreshToken() {
			// given
			final String refreshToken = "VALID_TOKEN";
			doReturn(true)
				.when(jwtTokenProvider)
				.validateToken(any());
			doReturn(Optional.empty())
				.when(refreshTokenRepository)
				.findByToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(refreshToken));
		}

		@Test
		@DisplayName("[실패] - 저장된 리프레쉬 토큰이 다른 경우")
		void isDifferentRefreshToken() {
			// given
			final String request = "VALID_TOKEN";
			final RefreshToken refreshToken = RefreshToken.builder()
				.token("TOKEN_DIFFERENT")
				.userId(1)
				.build();
			doReturn(true)
				.when(jwtTokenProvider)
				.validateToken(any());
			doReturn(Optional.of(refreshToken))
				.when(refreshTokenRepository)
				.findByToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.reissueToken(request));
		}

		@Test
		@DisplayName("[성공] - 올바른 리프레쉬 토큰")
		void validRefreshToken() {
			// given
			final String request = "TOKEN_BEFORE";
			Token token = Token.builder()
				.accessToken("TOKEN_AFTER")
				.refreshToken("TOKEN_AFTER")
				.build();
			RefreshToken refreshToken = RefreshToken.builder()
				.token("TOKEN_BEFORE")
				.userId(1)
				.build();

			doReturn(true)
				.when(jwtTokenProvider)
				.validateToken(any());
			doReturn(Optional.of(refreshToken))
				.when(refreshTokenRepository)
				.findByToken(any());
			doReturn(token)
				.when(jwtTokenProvider)
				.createToken(any());

			// when
			Token result = userService.reissueToken(request);

			// then
			assertEquals(token.getRefreshToken(), result.getRefreshToken());
			assertEquals(token.getAccessToken(), result.getAccessToken());
		}
	}

	@Nested
	@DisplayName("토큰으로 회원 정보")
	class TokenToUserInfo {
		@Test
		@DisplayName("[실패] - 토큰이 올바르지 않은 경우")
		void notValidTokenError() {
			// given
			final String accessToken = "INVALID_TOKEN";
			doReturn(false)
				.when(jwtTokenProvider)
				.validateToken(any());

			// when

			// then
			assertThrows(InvalidRefreshTokenException.class, () -> userService.getUserResponse(accessToken));
		}
	}

	public UserInfo newUserInfo() {
		return UserInfo.builder()
			.email(email)
			.name(name)
			.build();
	}
}
