package com.ssafy.mylifencut.answer.dto;

import org.springframework.lang.Nullable;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerResponse {
	private int id;
	private Integer articleId;
	private Integer questionId;
	private String imgUrl;
	private String contents;
	private State state;

	@Nullable
	public static AnswerResponse of(Answer answer) {
		return AnswerResponse.builder()
			.id(answer.getId())
			.articleId(answer.getArticle().getId())
			.imgUrl(answer.getImgUrl())
			.questionId(answer.getQuestionId())
			.contents(answer.getContents())
			.state(answer.getState())
			.build();
	}
}
