package com.ssafy.mylifencut.article.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.dto.ArticleResponse;
import com.ssafy.mylifencut.article.exception.NotFoundUserException;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("여행일지 service 테스트코드")
class ArticleServiceTest {
	@InjectMocks
	private ArticleService articleService;
	@Mock
	private ArticleRepository articleRepository;

	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("[여행일지 조회 실패] - 존재하지않는 UserId일 때")
	public void notFoundUserError() {
		//given
		final int userId = 5;
		doReturn(Optional.empty()).when(userRepository).findById(userId);

		//when
		final NotFoundUserException result = assertThrows(NotFoundUserException.class
			, () -> articleService.retrieveArticles(userId));

		//then
		assertEquals(result.getMessage(), ArticleConstant.NOT_FOUND_USER_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("[여행일지 조회 성공]")
	public void retrieveArticleSuccess() {
		//given
		final int userId = 5;
		final String userName = "여행일지유저";
		final User user = User.builder()
			.name(userName)
			.build();
		doReturn(Optional.of(user)).when(userRepository).findById(userId);

		final List<Article> articles = new ArrayList<>();
		articles.add(Article.builder().user(user).build());
		articles.add(Article.builder().user(user).build());
		doReturn(articles).when(articleRepository).findAllByUserId(userId);

		//when
		final List<ArticleResponse> result = articleService.retrieveArticles(userId);
		assertNotNull(result);
		assertEquals(result.size(), 2);
		for (ArticleResponse response : result) {
			assertEquals(response.getUser().getName(), userName);
		}
	}
}