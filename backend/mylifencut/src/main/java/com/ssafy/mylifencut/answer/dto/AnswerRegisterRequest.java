package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.State;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AnswerRegisterRequest {
	private Integer userId;
	private Integer questionId;
	private String contents;
	private State state;
}
