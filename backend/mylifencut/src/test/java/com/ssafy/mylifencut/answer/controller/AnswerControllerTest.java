package com.ssafy.mylifencut.answer.controller;

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
import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.dto.AnswerResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.aop.ExceptionAdvice;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

	@InjectMocks
	private AnswerController answerController;
	@Mock
	private AnswerService answerService;
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
		final AnswerRequest answerRequest =
			new AnswerRequest(1, 1, "답변 등록", State.OPEN);
		doThrow(new InvalidStateException(AnswerConstant.INVALID_STATE_ERROR_MESSAGE))
			.when(answerService)
			.createAnswer(answerRequest);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRequest))
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
		final AnswerRequest answerRequest =
			new AnswerRequest(1, 1, "답변 등록", State.CLOSE);
		final AnswerResponse answerResponse = AnswerResponse.builder()
			.id(1)
			.articleId(1)
			.questionId(answerRequest.getQuestionId())
			.contents(answerRequest.getContents())
			.state(answerRequest.getState())
			.build();
		doReturn(answerResponse)
			.when(answerService)
			.createAnswer(answerRequest);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isOk());

		final AnswerResponse response = gson.fromJson(resultActions.andReturn()
			.getResponse()
			.getContentAsString(StandardCharsets.UTF_8), AnswerResponse.class);

		assertEquals(answerResponse, response);
	}
}