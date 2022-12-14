package com.ssafy.mylifencut.answer.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import org.springframework.lang.Nullable;

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
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

	@Nullable
	private String imgUrl;

	@Enumerated(EnumType.STRING)
	private State state;

	@Builder.Default
	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
	private List<IsLike> likes = new ArrayList<>();

	public static Answer from(AnswerRegisterRequest answerRegisterRequest, Article article) {

		return Answer.builder()
			.article(article)
			.contents(answerRegisterRequest.getContents())
			.questionId(answerRegisterRequest.getQuestionId())
			.contents(answerRegisterRequest.getContents())
			.imgUrl(answerRegisterRequest.getImgUrl())
			.state(answerRegisterRequest.getState())
			.build();
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public boolean isCanNotBeOpenedAnswer() {
		return this.questionId != AnswerConstant.OPENABLE_QUESTION_ID && this.state.equals(State.OPEN);
	}
}
