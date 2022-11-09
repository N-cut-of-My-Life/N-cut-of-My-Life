package com.ssafy.mylifencut.article.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
@DisplayName("여행일지 repository 테스트")
class ArticleRepositoryTest {
	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void repositoryIsNotNull() {
		assertNotNull(articleRepository);
	}

	@Test
	@DisplayName("[성공] 여행일지가 없을 때(사이즈가 0이어야함) 조회")
	void retrieveArticle_empty() {
		//given
		final List<Article> articles;

		//when
		articles = articleRepository.findAllByUserId(1);

		//then
		assertNotNull(articles);
		assertEquals(articles.size(), 0);
	}

	@Test
	@DisplayName("[성공] 여행일지 저장 후 조회")
	void SaveArticleAndRetrieve() {
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
