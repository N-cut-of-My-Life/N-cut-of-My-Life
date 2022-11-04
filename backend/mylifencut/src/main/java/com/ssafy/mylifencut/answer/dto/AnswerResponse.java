package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class AnswerResponse {
	private int id;
	private Integer articleId;
	private Integer questionId;
	private String contents;
	private State state;

	public static AnswerResponse of(Answer answer) {
		return AnswerResponse.builder()
			.id(answer.getId())
			.articleId(answer.getArticle().getId())
			.questionId(answer.getQuestionId())
			.contents(answer.getContents())
			.state(answer.getState())
			.build();
	}
}
