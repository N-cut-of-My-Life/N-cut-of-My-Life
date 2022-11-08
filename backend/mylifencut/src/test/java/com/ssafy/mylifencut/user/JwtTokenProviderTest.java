package com.ssafy.mylifencut.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.user.dto.TokenResponse;
import com.ssafy.mylifencut.user.service.UserService;

@DisplayName("JWT 토큰 테스트")
@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

	@InjectMocks
	private JwtTokenProvider jwtTokenProvider;
	@Mock
	private UserService userService;

	@BeforeEach
	void init() {
		jwtTokenProvider.init();
	}

	@DisplayName("유효한 토큰이 생성된 후 원하는 playload로 변환되는지 검증한다.")
	@Test
	void checkPayloadAfterIssuingToken() {
		// given
		final String userId = "1";

		//when
		final TokenResponse accessToken = jwtTokenProvider.createToken(userId);

		// then
		assertEquals(userId, jwtTokenProvider.getUserId(accessToken.getAccessToken()));
	}

	@DisplayName("엑세스 토큰 검증")
	@Nested
	class TokenValidationTest {

		@DisplayName("토큰 검증 정확한 토큰")
		@Test
		void validToken() {
			// given
			final String userId = "1";

			// when
			final TokenResponse accessToken = jwtTokenProvider.createToken(userId);

			// then
			assertTrue(jwtTokenProvider.validateToken(accessToken.getAccessToken()));
		}

		@DisplayName("토큰 검증 만료된 토큰이면 false")
		@Test
		void expiredToken() {
			// given
			final String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjY3Nzc5NTYyLCJleHAiOjE2Njc3Nzk1NjN9.YafKFAra_MdrtlSwimpYIjwaL9TKVTkNuLe5fgQOY2w";

			// when
			final boolean result = jwtTokenProvider.validateToken(invalidToken);

			// then
			assertFalse(result);
		}

		@DisplayName("토큰 검증 발급되지 않은 토큰이면 false")
		@Test
		void invalidToken() {
			// given
			final String unissuedAccessToken = "유효하지 않은 토큰";

			// when
			boolean result = jwtTokenProvider.validateToken(unissuedAccessToken);

			//then
			assertFalse(result);
		}
	}

	@Nested
	@DisplayName("리프레쉬 토큰 검증")
	class refreshTokenValidation {
		@DisplayName("만료된 리프레쉬 토큰인 경우")
		@Test
		void invalidToken() {
			// given
			final String unissuedAccessToken = "유효하지 않은 토큰";

			// when
			boolean result = jwtTokenProvider.validateToken(unissuedAccessToken);

			//then
			assertFalse(result);
		}

		@DisplayName("기간이 남아있는 리프레쉬 토큰인 경우")
		@Test
		void validToken() {
			// given
			final String unissuedAccessToken = "유효하지 않은 토큰";

			// when
			boolean result = jwtTokenProvider.validateToken(unissuedAccessToken);

			//then
			assertFalse(result);
		}
	}
}