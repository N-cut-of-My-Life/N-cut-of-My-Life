package com.ssafy.mylifencut.article.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.dto.ArticleRegisterRequest;
import com.ssafy.mylifencut.article.dto.ArticleRetrieveResponse;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.UserConstant;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
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
	public void retrieveArticle_notFoundUserError() {
		//given
		final int userId = 5;
		doReturn(Optional.empty()).when(userRepository).findById(userId);

		//when
		final UserNotFoundException result = assertThrows(UserNotFoundException.class
			, () -> articleService.retrieveArticles(userId));

		//then
		assertEquals(result.getMessage(), UserConstant.USER_NOT_FOUND_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("[여행일지 조회 성공]")
	public void retrieveArticle_success() {
		//given
		final int userId = 5;
		final String userName = "여행일지유저";
		final User user = User.builder()
			.name(userName)
			.build();
		final List<Article> articles = new ArrayList<>();
		articles.add(Article.builder().user(user).build());
		articles.add(Article.builder().user(user).build());

		//when
		doReturn(Optional.of(user)).when(userRepository).findById(userId);
		doReturn(articles).when(articleRepository).findAllByUserId(userId);

		//then
		final List<ArticleRetrieveResponse> result = articleService.retrieveArticles(userId);
		assertNotNull(result);
		assertEquals(result.size(), 2);
		for (ArticleRetrieveResponse response : result) {
			assertEquals(response.getUser().getName(), userName);
		}
	}

	@Test
	@DisplayName("[여행일지 등록 실패] - 존재하지 않는 userId일 때")
	public void registerArticle_notFoundUserError() {
		//given
		doReturn(Optional.empty()).when(userRepository).findById(5);

		//when
		final UserNotFoundException result = assertThrows(UserNotFoundException.class
			, () -> articleService.createArticle(new ArticleRegisterRequest(
				1, User.builder().id(5).build(), Collections.emptyList(), LocalDateTime.now())
			)
		);

		//then
		assertEquals(result.getMessage(), UserConstant.USER_NOT_FOUND_ERROR_MESSAGE);
	}
}