package com.ssafy.mylifencut.answer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRequest;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

	@InjectMocks
	private AnswerController answerController;
	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	public void init() {
		gson = new Gson();
		mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();
	}

	@Test
	@DisplayName("답변 등록 실패 - 잘못된 행성에 공개여부 선택 된 경우")
	public void createAnswer() throws Exception {
		// given
		final String url = "/answer";
		final AnswerRequest answerRequest =
			new AnswerRequest(1, 1, "답변 등록", State.OPEN);

		// when
		final ResultActions resultActions = mockMvc.perform(
			MockMvcRequestBuilders.post(url)
				.content(gson.toJson(answerRequest))
				.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isBadRequest());
	}
}