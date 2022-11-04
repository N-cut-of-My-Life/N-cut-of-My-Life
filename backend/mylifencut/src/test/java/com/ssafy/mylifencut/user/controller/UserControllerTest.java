package com.ssafy.mylifencut.user.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.service.UserService;

@ExtendWith(MockitoExtension.class)
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

	@Test
	@DisplayName("카카오 회원가입 - AccessToken 오류")
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
}
