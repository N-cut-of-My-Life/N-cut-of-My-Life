package com.ssafy.mylifencut.article.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.article.service.ArticleService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("여행일지 controller 테스트 코드")
class ArticleControllerTest {
	@InjectMocks
	private ArticleController articleController;

	@Mock
	private ArticleService articleService;

	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	public void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(articleController)
			.setControllerAdvice(new ExceptionAdvice())
			.build();
	}

	@Test
	@DisplayName("[여행일지 조회 실패] - 존재하지 않는 userId일때")
	public void retrieveArticle_notFoundUserError() throws Exception {
		final int userId = -1;
		final String url = "/article/" + userId;

		//given
		doThrow(new UserNotFoundException())
			.when(articleService)
			.retrieveArticles(userId);

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url));

		//then
		resultActions.andExpect(status().isBadRequest());

		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
		Map map = (Map)response.getData();
		assertNull(response.getData());
		assertFalse(response.isSuccess());
		assertEquals(UserConstant.USER_NOT_FOUND_ERROR_MESSAGE, response.getMessage());
	}
}