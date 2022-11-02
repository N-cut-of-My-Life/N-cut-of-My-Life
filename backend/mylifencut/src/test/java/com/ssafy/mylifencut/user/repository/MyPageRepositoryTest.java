package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
public class MyPageRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void repositoryIsNotNull() {
		assertNotNull(userRepository);
	}

	@Test
	@DisplayName("마이페이지에서 과거 기록 리스트가 없을 때(사이즈가 0이어야함) - 성공")
	public void RetrieveMyPageEmpty() {
		//given
		final User user = user();

		//when
		final User savedUser = userRepository.save(user);

		//then
		assertNotNull(savedUser);

		final Optional<User> foundUser = userRepository.findById(savedUser.getId());
		assertTrue(foundUser.isPresent());
		assertNotNull(foundUser.get());
		assertEquals(savedUser.getId(), foundUser.get().getId());
		assertEquals(foundUser.get().getArticles().size(), 0);
	}

	@Test
	@DisplayName("마이페이지에서 과거 기록 사이즈가 2이어야함 - 성공")
	public void RetrieveMyPageSizeTwo() {
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
		articleRepository.save(article1);
		articleRepository.save(article2);

		//when
		user.addArticles(article1);
		user.addArticles(article2);
		final User savedUser = userRepository.save(user);

		//then
		assertNotNull(savedUser);
		final Optional<User> foundUser = userRepository.findById(savedUser.getId());
		assertTrue(foundUser.isPresent());
		assertNotNull(foundUser.get());
		assertEquals(savedUser.getId(), foundUser.get().getId());

		final List<Article> articles = foundUser.get().getArticles();
		assertEquals(articles.size(), 2);
		for (Article article : articles) {
			assertEquals(article.getUser().getId(), savedUser.getId());
		}
	}

	private User user() {
		return User.builder()
			.email("test@email.com")
			.name("이름")
			.build();
	}
}
