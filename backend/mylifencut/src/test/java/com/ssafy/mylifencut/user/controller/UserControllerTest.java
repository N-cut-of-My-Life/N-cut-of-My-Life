package com.ssafy.mylifencut.user.controller;

import static com.ssafy.mylifencut.user.UserConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.dto.TokenRequest;
import com.ssafy.mylifencut.user.dto.TokenResponse;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.exception.InvalidRefreshTokenException;
import com.ssafy.mylifencut.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원 관리 컨트롤러 테스트")
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	@Mock
	private UserService userService;

	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	private void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new ExceptionAdvice())
			.build();
	}

	@Nested
	@DisplayName("카카오 로그인")
	class loginTest {
		@Test
		@DisplayName("카카오 로그인 AccessToken 오류")
		void invalidToken() throws Exception {
			// given
			final String url = "/user/login";
			final Map<String, String> token = new HashMap<>();
			token.put("accessToken", "INVALID_TOKEN");
			doThrow(new InvalidKakaoAccessTokenException())
				.when(userService)
				.kakaoLogin(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(token))
					.contentType(MediaType.APPLICATION_JSON)
			);

			//then
			resultActions.andExpect(status().isBadRequest());
			BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			assertFalse(response.isSuccess());
			assertEquals(response.getMessage(), INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("로그인 성공")
		void loginSuccess() throws Exception {
			// given
			final String url = "/user/login";
			final Map<String, String> token = new HashMap<>();
			token.put("accessToken", "INVALID_TOKEN");
			final TokenResponse jwtToken = TokenResponse.builder()
				.accessToken("NEW_TOKEN")
				.refreshToken("NEW_TOKEN")
				.build();

			doReturn(jwtToken)
				.when(userService)
				.kakaoLogin(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(token))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isOk());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			Map map = (Map)response.getData();
			assertEquals(map.get("accessToken"), jwtToken.getAccessToken());
			assertEquals(map.get("refreshToken"), jwtToken.getRefreshToken());
			assertTrue(response.isSuccess());
			assertEquals(response.getMessage(), KAKAO_LOGIN_SUCCESS_MESSAGE);
		}
	}

	@Nested
	@DisplayName("리프레쉬 토큰 테스트")
	class refreshTokenTest {

		@Test
		@DisplayName("잘못된 리프레쉬 토큰")
		void expiredRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("INVALID_TOKEN")
				.refreshToken("INVALID_TOKEN")
				.build();
			doThrow(new InvalidRefreshTokenException())
				.when(userService)
				.reissueToken(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(tokenRequest))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isBadRequest());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			assertFalse(response.isSuccess());
			assertEquals(response.getMessage(), INVALID_REFRESH_TOKEN_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("만료된 리프레쉬 토큰")
		void invalidRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("EXPIRED_TOKEN")
				.refreshToken("EXPIRED_TOKEN")
				.build();
			doThrow(new InvalidRefreshTokenException())
				.when(userService)
				.reissueToken(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(tokenRequest))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isBadRequest());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			assertFalse(response.isSuccess());
			assertEquals(response.getMessage(), INVALID_REFRESH_TOKEN_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("유효한 리프레쉬 토큰")
		void validRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final TokenRequest tokenRequest = TokenRequest.builder()
				.accessToken("VALID_TOKEN")
				.refreshToken("VALID_TOKEN")
				.build();
			final TokenResponse tokenResponse = TokenResponse.builder()
				.accessToken("NEW_TOKEN")
				.refreshToken("NEW_TOKEN")
				.build();
			doReturn(tokenResponse)
				.when(userService)
				.reissueToken(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(url)
					.content(gson.toJson(tokenRequest))
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isOk());

			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

			Map map = (Map)response.getData();
			assertEquals(map.get("accessToken"), tokenResponse.getAccessToken());
			assertEquals(map.get("refreshToken"), tokenResponse.getRefreshToken());
			assertTrue(response.isSuccess());
			assertEquals(response.getMessage(), TOKEN_REISSUE_SUCCESS_MESSAGE);
		}
	}
}
