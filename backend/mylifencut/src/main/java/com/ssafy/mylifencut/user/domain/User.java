package com.ssafy.mylifencut.user.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.user.dto.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@Table(name = "member")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<Article> articles = new ArrayList<>();

	private String email;

	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<IsLike> likes = new ArrayList<>();

	public void addArticle(Article article) {
		this.articles.add(article);
	}

	public static User from(UserInfo userInfo) {
		return User.builder()
			.name(userInfo.getName())
			.email(userInfo.getEmail())
			.build();
	}
}
