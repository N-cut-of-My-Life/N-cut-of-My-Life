package com.ssafy.mylifencut.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import com.ssafy.mylifencut.user.JwtTokenProvider;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.service.UserService;

@ExtendWith(MockitoExtension.class)
@DisplayName("회원 관리 컨트롤러 테스트")
public class UserControllerTest {

	@InjectMocks
	private UserController userController;
	@Mock
	private UserService userService;
	@Mock
	private JwtTokenProvider jwtTokenProvider;

	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	private void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(userController)
			.setControllerAdvice(new ExceptionAdvice())
			.build();
	}

	@Test
	@DisplayName("카카오 로그인 - AccessToken 오류")
	public void invalidToken() throws Exception {
		// given
		final String url = "/user/login";
		final String token = "INVALID_TOKEN";
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
	}

	@Test
	@DisplayName("카카오 로그인 - 성공")
	public void loginSuccess() throws Exception {
		// given
		final String url = "/user/login";
		final String accessToken = "DUMMY_TOKEN";
		final String jwtToken = "JWT_TOKEN";

		doReturn(1)
			.when(userService)
			.kakaoLogin(any());
		doReturn(jwtToken)
			.when(jwtTokenProvider)
			.createToken(any());

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(accessToken))
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isOk());
		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
		assertEquals(response.getData(), jwtToken);
	}
}
