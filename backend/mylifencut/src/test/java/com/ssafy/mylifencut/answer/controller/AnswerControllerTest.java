package com.ssafy.mylifencut.answer.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;

@ExtendWith(MockitoExtension.class)
class AnswerControllerTest {

	@InjectMocks
	private AnswerController answerController;
	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(answerController).build();
	}

	@Test
	@DisplayName("Mockmvc가 Null이 아님")
	public void mockMvcIsNotNull() throws Exception {
		assertNotNull(answerController);
		assertNotNull(mockMvc);
	}
}