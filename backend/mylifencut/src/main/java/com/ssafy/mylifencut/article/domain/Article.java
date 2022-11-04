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
import com.ssafy.mylifencut.article.dto.ArticleRegisterRequest;
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

	private LocalDateTime createDate;

	public static Article from(ArticleRegisterRequest articleRegisterRequest, User user) {
		return Article.builder()
			.user(user)
			.createDate(articleRegisterRequest.getCreateDate())
			.build();
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
		if (answer.getArticle() != this) {
			answer.setArticle(this);
		}
	}
}
