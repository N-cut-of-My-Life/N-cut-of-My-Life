package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
public class ArticleRepositoryTest {
	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void repositoryIsNotNull() {
		assertNotNull(articleRepository);
	}

	@Test
	@DisplayName("마이페이지에서 과거 기록 리스트가 없을 때(사이즈가 0이어야함) - 성공")
	public void RetrieveMyPageEmpty() {
		//given
		final List<Article> articles;

		//when
		articles = articleRepository.findByUserId(1);

		//then
		assertNotNull(articles);
		assertEquals(articles.size(), 0);
	}

	@Test
	@DisplayName("마이페이지에서 article을 저장 후 조회 - 성공")
	public void SaveArticleAndRetrieve() {
		//given
		final User user = user();
		final Article article1 = Article.builder()
			.user(user)
			.answers(Collections.emptyList())
			.createDate(LocalDateTime.now())
			.build();
		final Article article2 = Article.builder()
			.user(user)
			.answers(Collections.emptyList())
			.createDate(LocalDateTime.now())
			.build();

		//when
		Article savedArticle1 = articleRepository.save(article1);
		Article savedArticle2 = articleRepository.save(article2);

		savedArticle1.getUser().addArticle(article1);
		savedArticle2.getUser().addArticle(article2);

		//then
		final List<Article> articles = articleRepository.findAll();
		assertNotNull(articles);
		assertEquals(articles.size(), 2);

		for (Article article : articles) {
			assertEquals(article.getUser().getName(), user.getName());
		}
	}

	private User user() {
		return User.builder()
			.email("test@email.com")
			.name("이름")
			.build();
	}
}
