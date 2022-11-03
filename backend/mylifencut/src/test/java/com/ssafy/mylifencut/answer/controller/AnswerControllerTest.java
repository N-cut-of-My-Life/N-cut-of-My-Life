package com.ssafy.mylifencut.answer.controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.LikeConstant;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.service.LikeService;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

	@InjectMocks
	private AnswerController answerController;
	@Mock
	private AnswerService answerService;

	@Mock
	private LikeService likeService;

	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	public void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(answerController)
			.setControllerAdvice(new ExceptionAdvice())
			.build();
	}

	@Test
	@DisplayName("답변 등록 실패 - 잘못된 행성에 공개여부 선택 된 경우")
	public void invalidState() throws Exception {
		// given
		final String url = "/answer";
		final AnswerRegisterRequest answerRegisterRequest =
			new AnswerRegisterRequest(1, 1, "답변 등록", State.OPEN);
		doThrow(new InvalidStateException())
			.when(answerService)
			.createAnswer(answerRegisterRequest);
		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);
		// then
		resultActions.andExpect(status().isBadRequest());
	}
	@Test
	@DisplayName("답변 등록 성공")
	public void createAnswer() throws Exception {
		// given
		final String url = "/answer";
		final AnswerRegisterRequest answerRegisterRequest =
			new AnswerRegisterRequest(1, 1, "답변 등록", State.CLOSE);
		final AnswerRegisterResponse answerRegisterResponse = AnswerRegisterResponse.builder()
			.id(1)
			.articleId(1)
			.questionId(answerRegisterRequest.getQuestionId())
			.contents(answerRegisterRequest.getContents())
			.state(answerRegisterRequest.getState())
			.build();
		doReturn(answerRegisterResponse)
			.when(answerService)
			.createAnswer(answerRegisterRequest);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRegisterRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isOk());

		final BaseResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), BaseResponse.class);

		Map map = (Map)response.getData();
		assertEquals((double)answerRegisterResponse.getId(), map.get("id"));
		assertEquals((double)answerRegisterResponse.getArticleId(), map.get("articleId"));
		assertEquals((double)answerRegisterResponse.getQuestionId(), map.get("questionId"));
		assertEquals(answerRegisterResponse.getContents(), map.get("contents"));
		assertEquals(answerRegisterResponse.getState().toString(), map.get("state"));
	}

	@Test
	@DisplayName("좋아요 추가 실패 - 이미 좋아요가 추가된 답변에 좋아요 등록")
	public void alreadyLike() throws Exception {

		//given
		final String url = "/answer/3/1";
		final Integer answerId = 3;
		final Integer userId = 1;

		doThrow(new AlreadyLikeException(LikeConstant.ALREADY_LIKE_EXIST_ERROR_MESSAGE))
			.when(likeService)
			.createLike(userId, answerId);
		//when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.contentType(MediaType.APPLICATION_JSON)
		);
		//then
		resultActions.andExpect(status().isBadRequest());

	}
}