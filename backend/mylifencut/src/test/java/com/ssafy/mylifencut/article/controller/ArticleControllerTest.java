package com.ssafy.mylifencut.article.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.dto.ArticleRequest;
import com.ssafy.mylifencut.article.dto.ArticleResponse;
import com.ssafy.mylifencut.article.exception.AnswersSizeIsNotEnough;
import com.ssafy.mylifencut.article.service.ArticleService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.domain.User;
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

	@Nested
	@DisplayName("[여행일지 조회]")
	class RetrieveTest {
		@Test
		@DisplayName("[실패] - 존재하지 않는 userId일때")
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
			assertNull(response.getData());
			assertFalse(response.isSuccess());
			assertEquals(UserConstant.USER_NOT_FOUND_ERROR_MESSAGE, response.getMessage());
		}

		@Test
		@DisplayName("[성공]")
		public void retrieveArticle_success() throws Exception {
			//given
			final int userId = 5;
			final String url = "/article/" + userId;

			final List<ArticleResponse> articleResponses = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				articleResponses.add(ArticleResponse.builder()
					.id(i + 1)
					.user(User.builder().id(userId).build())
					.createDate(LocalDateTime.now())
					.build());
			}
			doReturn(articleResponses).when(articleService).retrieveArticles(userId);

			//when
			final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url));

			resultActions.andExpect(status().isOk());
			final BaseResponse response = gson.fromJson(resultActions.andReturn()
				.getResponse()
				.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

			//then
			assertNotNull(response);
			assertTrue(response.isSuccess());
			assertEquals(ArticleConstant.RETRIEVE_SUCCESS_MESSAGE, response.getMessage());
			List<ArticleResponse> list = (List<ArticleResponse>)response.getData();
			for (int i = 0; i < list.size(); i++) {
				Map articleResponse = (Map)list.get(i);
				assertEquals((double)(i + 1), articleResponse.get("id"));
			}
		}

	}

	@ParameterizedTest
	@MethodSource("registerArticleErrorParameter")
	@DisplayName("[여행일지 등록 실패] - service에서 예외가 발생했을 때")
	public void registerArticle_error(RuntimeException exception, String errorMessage) throws
		Exception {
		//given
		final String url = "/article";
		final ArticleRequest articleRequest = ArticleRequest.builder()
			.userId(1)
			.build();
		doThrow(exception)
			.when(articleService)
			.createArticle(any(ArticleRequest.class));

		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(articleRequest)));

		//then
		resultActions.andExpect(status().isBadRequest());
		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
		assertFalse(response.isSuccess());
		assertEquals(errorMessage, response.getMessage());
		assertNull(response.getData());
	}

	private static Stream<Arguments> registerArticleErrorParameter() {
		return Stream.of(
			Arguments.of(new UserNotFoundException(), UserConstant.USER_NOT_FOUND_ERROR_MESSAGE),
			Arguments.of(new AnswersSizeIsNotEnough(),
				ArticleConstant.ARTICLE_ANSWERS_SIZE_IS_NOT_ENOUGH_ERROR_MESSAGE)
		);
	}

	@Test
	@DisplayName("[여행일지 등록 성공]")
	public void registerArticle_success() throws Exception {
		//given
		final String url = "/article";
		final ArticleRequest articleRequest = ArticleRequest.builder()
			.userId(1)
			.build();
		//when
		final ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders
			.post(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(gson.toJson(articleRequest)));

		//then
		resultActions.andExpect(status().isOk());
		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);
		assertTrue(response.isSuccess());
		assertEquals(ArticleConstant.REGISTER_SUCCESS_MESSAGE, response.getMessage());
		assertNull(response.getData());
	}
}