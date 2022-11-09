package com.ssafy.mylifencut.article.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.article.dto.ArticleRequest;
import com.ssafy.mylifencut.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder.Default
	@OneToMany(mappedBy = "article")
	private List<Answer> answers = new ArrayList<>();

	@Builder.Default
	private LocalDateTime createDate = LocalDateTime.now();

	public static Article from(ArticleRequest articleRequest, User user) {
		return Article.builder()
			.user(user)
			.createDate(articleRequest.getCreateDate())
			.build();
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
		answer.setArticle(this);
	}
}
