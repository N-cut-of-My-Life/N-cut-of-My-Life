package com.ssafy.mylifencut.article.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.repository.ArticleRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("여행일지 service 테스트코드")
class ArticleServiceTest {
	@InjectMocks
	private ArticleService articleService;
	@Mock
	private ArticleRepository articleRepository;

	@Test
	@DisplayName("[여행일지 조회 실패] - 존재하지않는 UserId일 때")
	public void notFoundUserError() {
		//given
		final List<Article> articles = Collections.emptyList();
		final int userId = 5;
		doReturn(articles).when(articleRepository.findByUserId(userId));

		//when
		final NotFoundUserException result = assertThrows(NotFoundUserException.class
			, () -> articleService.retrieveArticles(userId));

		//then
		assertEquals(result.getMessage(), ArticleConstant.NOT_FOUND_USER_ERROR_MESSAGE);
	}
}