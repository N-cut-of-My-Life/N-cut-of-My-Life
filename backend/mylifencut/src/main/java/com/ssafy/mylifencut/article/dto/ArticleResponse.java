package com.ssafy.mylifencut.article.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.mylifencut.answer.dto.AnswerResponse;
import com.ssafy.mylifencut.article.domain.Article;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticleResponse {
	private Integer id;
	private Integer userId;
	private List<AnswerResponse> answers;
	private LocalDateTime createDate;

	public static ArticleResponse of(Article article) {
		return ArticleResponse.builder()
			.id(article.getId())
			.userId(article.getUser().getId())
			.answers(article.getAnswers().stream()
				.map(AnswerResponse::of)
				.collect(Collectors.toList()))
			.createDate(article.getCreateDate())
			.build();
	}
}
