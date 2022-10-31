package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.State;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AnswerRequest {
	private Integer userId;
	private Integer questionId;
	private String contents;
	private State state;
}
