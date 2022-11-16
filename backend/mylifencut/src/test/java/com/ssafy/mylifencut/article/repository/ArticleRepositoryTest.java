package com.ssafy.mylifencut.article.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.article.domain.Article;

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
		assertEquals(0, articles.size());
	}

	@Test
	@DisplayName("[성공] 여행일지 저장 후 조회")
	void SaveArticleAndRetrieve() {
		//given
		final Article article1 = Article.builder()
			.answers(Collections.emptyList())
			.build();
		final Article article2 = Article.builder()
			.answers(Collections.emptyList())
			.build();

		//when
		articleRepository.save(article1);
		articleRepository.save(article2);

		final List<Article> articles = articleRepository.findAll();

		//then
		assertNotNull(articles);
		assertEquals(2, articles.size());
	}
}
