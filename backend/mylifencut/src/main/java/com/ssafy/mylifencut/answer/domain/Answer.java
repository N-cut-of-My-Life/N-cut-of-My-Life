package com.ssafy.mylifencut.answer.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "article_id")
	private Article article;

	private Integer questionId;

	private String contents;

	@Enumerated(EnumType.STRING)
	private State state;

	@OneToMany(mappedBy = "answer")
	private List<IsLike> likes;

	public static Answer from(AnswerRequest answerRequest, Article article) {

		return Answer.builder()
			.article(article)
			.contents(answerRequest.getContents())
			.questionId(answerRequest.getQuestionId())
			.contents(answerRequest.getContents())
			.state(answerRequest.getState())
			.build();
	}
}
