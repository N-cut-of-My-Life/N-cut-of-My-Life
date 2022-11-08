package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class AnswerRegisterRequest {
	private Integer userId;
	private Integer questionId;
	private String contents;
	private String imgUrl;
	private State state;
}
