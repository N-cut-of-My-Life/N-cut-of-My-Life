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

import com.ssafy.mylifencut.user.dto.Token;
import com.ssafy.mylifencut.user.service.UserService;

@DisplayName("[JWT 토큰]")
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

	@DisplayName("[성공] - 토큰 생성 후 payload검증")
	@Test
	void checkPayloadAfterIssuingToken() {
		// given
		final String userId = "1";

		//when
		final Token accessToken = jwtTokenProvider.createToken(userId);

		// then
		assertEquals(userId, jwtTokenProvider.getUserId(accessToken.getAccessToken()));
		System.out.println(accessToken.getRefreshToken());
	}

	@DisplayName("[엑세스 토큰]")
	@Nested
	class TokenValidationTest {

		@DisplayName("[성공] - 정확한 토큰")
		@Test
		void validToken() {
			// given
			final String userId = "1";

			// when
			final Token accessToken = jwtTokenProvider.createToken(userId);

			// then
			assertTrue(jwtTokenProvider.validateToken(accessToken.getAccessToken()));
		}

		@DisplayName("[실패] - 만료된 토큰")
		@Test
		void expiredToken() {
			// given
			final String invalidToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjY3Nzc5NTYyLCJleHAiOjE2Njc3Nzk1NjN9.YafKFAra_MdrtlSwimpYIjwaL9TKVTkNuLe5fgQOY2w";

			// when
			final boolean result = jwtTokenProvider.validateToken(invalidToken);

			// then
			assertFalse(result);
		}

		@DisplayName("[실패] - 발급되지 않은 토큰")
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
	@DisplayName("[리프레쉬 토큰]")
	class refreshTokenValidation {
		@DisplayName("[실패] - 만료된 토큰")
		@Test
		void expiredToken() {
			// given
			final String expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2Njc4Njg1NTh9.SMOp2mmYJ5GFhsxQb2_22hG0xDc5uEfX5deiAwxJ0oU";

			// when
			boolean result = jwtTokenProvider.validateToken(expiredToken);

			//then
			assertFalse(result);
		}

		@DisplayName("[성공] - 올바른 리프레쉬 토큰")
		@Test
		void validToken() {
			// given
			final Token token = jwtTokenProvider.createToken("1");

			// when
			boolean result = jwtTokenProvider.validateToken(token.getRefreshToken());

			//then
			assertTrue(result);
		}

		@DisplayName("[실패] - 발급되지 않은 토큰")
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
}