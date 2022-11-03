package com.ssafy.mylifencut.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.user.domain.User;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class ArticleRetrieveResponse {
	private Integer id;
	private User user;
	private List<Answer> answers;
	private LocalDateTime createDate;

	public static ArticleRetrieveResponse of(Article article) {
		return ArticleRetrieveResponse.builder()
			.id(article.getId())
			.user(article.getUser())
			.answers(article.getAnswers())
			.createDate(article.getCreateDate())
			.build();
	}
}
