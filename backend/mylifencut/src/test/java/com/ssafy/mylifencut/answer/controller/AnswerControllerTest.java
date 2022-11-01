package com.ssafy.mylifencut.answer.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.dto.BaseResponse;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

	@MockBean
	AnswerService answerService;

	private MockMvc mockMvc;

	@Test
	@DisplayName("주어진 질문에 대한 답변을 등록한다.")
	void createAnswer() throws Exception {
		//given
		AnswerRequest answerRequest = new AnswerRequest(3, 3, "답변 내용", State.OPEN);
		BaseResponse baseResponse = BaseResponse.from(true, "답변이 등록되었습니다.");
		given(answerService.register(answerRequest))
			.willReturn(baseResponse);

		//when
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/answer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(new Gson().toJson(answerRequest))
		);

		//then
		MvcResult mvcResult = resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("isSuccess", baseResponse.isSuccess()).exists())
			.andExpect(jsonPath("message", baseResponse.getMessage()).exists())
			.andExpect(jsonPath("data", baseResponse.getData()).doesNotExist())
			.andReturn();

	}
}