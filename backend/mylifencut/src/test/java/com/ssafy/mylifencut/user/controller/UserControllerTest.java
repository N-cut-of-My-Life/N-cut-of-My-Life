package com.ssafy.mylifencut.user.controller;

import static com.ssafy.mylifencut.user.UserConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

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
import com.ssafy.mylifencut.user.dto.Token;
import com.ssafy.mylifencut.user.dto.UserResponse;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.exception.InvalidRefreshTokenException;
import com.ssafy.mylifencut.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("[유저 컨트롤러]")
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
		@DisplayName("[실패] - AccessToken 오류")
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
			assertEquals(INVALID_KAKAO_ACCESS_TOKEN_ERROR_MESSAGE, response.getMessage());
		}

		@Test
		@DisplayName("[성공] - 로그인 성공")
		void loginSuccess() throws Exception {
			// given
			final String url = "/user/login";
			final Map<String, String> token = new HashMap<>();
			token.put("accessToken", "VALID_TOKEN");
			final Token jwtToken = Token.builder()
				.accessToken("NEW_TOKEN")
				.refreshToken("NEW_TOKEN")
				.build();
			final UserResponse userResponse = UserResponse.builder()
				.userId(1)
				.name("홍길동")
				.email("ssafy@email.com")
				.accessToken("NEW_TOKEN")
				.build();

			doReturn(jwtToken)
				.when(userService)
				.kakaoLogin(any());
			doReturn(userResponse)
				.when(userService)
				.getUserResponse(jwtToken.getAccessToken());

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
			assertEquals(map.get("userId"), (double)userResponse.getUserId());
			assertEquals(map.get("name"), userResponse.getName());
			assertEquals(map.get("email"), userResponse.getEmail());
			assertEquals(map.get("accessToken"), userResponse.getAccessToken());
			assertTrue(response.isSuccess());
			assertEquals(KAKAO_LOGIN_SUCCESS_MESSAGE, response.getMessage());
			cookie().value("refreshToken", jwtToken.getRefreshToken());
		}
	}

	@Nested
	@DisplayName("[리프레쉬 토큰]")
	class refreshTokenTest {

		@Test
		@DisplayName("[실패] - 잘못된 리프레쉬 토큰")
		void expiredRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final Cookie refreshToken = new Cookie("refreshToken", "INVALID_TOKEN");
			doThrow(new InvalidRefreshTokenException())
				.when(userService)
				.reissueToken(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
					.cookie(refreshToken)
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isBadRequest());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			assertFalse(response.isSuccess());
			assertEquals(INVALID_REFRESH_TOKEN_ERROR_MESSAGE, response.getMessage());
		}

		@Test
		@DisplayName("[실패] - 만료된 리프레쉬 토큰")
		void invalidRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final Cookie refreshToken = new Cookie("refreshToken", "EXPIRED_TOKEN");
			doThrow(new InvalidRefreshTokenException())
				.when(userService)
				.reissueToken(any());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
					.cookie(refreshToken)
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isBadRequest());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
			assertFalse(response.isSuccess());
			assertEquals(INVALID_REFRESH_TOKEN_ERROR_MESSAGE, response.getMessage());
		}

		@Test
		@DisplayName("[성공] - 유효한 리프레쉬 토큰")
		void validRefreshToken() throws Exception {
			// given
			final String url = "/user/token";
			final Cookie refreshToken = new Cookie("refreshToken", "VALID_TOKEN");
			final Token token = Token.builder()
				.accessToken("NEW_TOKEN")
				.refreshToken("NEW_TOKEN")
				.build();
			final UserResponse userResponse = UserResponse.builder()
				.userId(1)
				.name("홍길동")
				.email("ssafy@email.com")
				.accessToken("NEW_TOKEN")
				.build();
			doReturn(token)
				.when(userService)
				.reissueToken(any());
			doReturn(userResponse)
				.when(userService)
				.getUserResponse(token.getAccessToken());

			// when
			final ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.get(url)
					.cookie(refreshToken)
					.contentType(MediaType.APPLICATION_JSON)
			);

			// then
			resultActions.andExpect(status().isOk());

			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

			Map map = (Map)response.getData();
			assertEquals(map.get("userId"), (double)userResponse.getUserId());
			assertEquals(map.get("name"), userResponse.getName());
			assertEquals(map.get("email"), userResponse.getEmail());
			assertEquals(map.get("accessToken"), userResponse.getAccessToken());
			assertTrue(response.isSuccess());
			assertEquals(TOKEN_REISSUE_SUCCESS_MESSAGE, response.getMessage());
			cookie().value("refreshToken", token.getRefreshToken());
		}
	}

	@Test
	@DisplayName("[실패] - 엑세스 토큰이 없는 경우")
	void noAccessToken() throws Exception {
		// given
		final String url = "/user/exception";

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.get(url)
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isForbidden());
		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
		assertFalse(response.isSuccess());
		assertEquals(INVALID_ACCESS_TOKEN_ERROR_MESSAGE, response.getMessage());
	}
}
