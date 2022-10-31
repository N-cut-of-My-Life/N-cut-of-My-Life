package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.article.domain.Article;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerResponse {
	private int id;
	private Article article;
	private Integer questionId;
	private String contents;
	private State state;

	public static AnswerResponse of(Answer answer) {
		return AnswerResponse.builder()
			.id(answer.getId())
			.article(answer.getArticle())
			.questionId(answer.getQuestionId())
			.contents(answer.getContents())
			.state(answer.getState())
			.build();
	}
}
