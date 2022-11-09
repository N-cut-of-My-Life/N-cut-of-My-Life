package com.ssafy.mylifencut.article.service;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;
import static com.ssafy.mylifencut.article.ArticleConstant.*;
import static com.ssafy.mylifencut.user.UserConstant.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.exception.CanNotBeOpenedAnswerException;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.dto.ArticleRequest;
import com.ssafy.mylifencut.article.dto.ArticleResponse;
import com.ssafy.mylifencut.article.exception.AnswersSizeIsNotEnoughException;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
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

	@Nested
	@DisplayName("[여행일지 조회]")
	class retrieveTest {
		@Test
		@DisplayName("[실패] - 존재하지않는 UserId일 때")
		void retrieveArticle_notFoundUserError() {
			//given
			final int userId = 5;
			doReturn(Optional.empty()).when(userRepository).findById(userId);

			//when
			final UserNotFoundException result = assertThrows(UserNotFoundException.class
				, () -> articleService.retrieveArticles(userId));

			//then
			assertEquals(USER_NOT_FOUND_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[성공]")
		void retrieveArticle_success() {
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
			final List<ArticleResponse> result = articleService.retrieveArticles(userId);
			assertNotNull(result);
			assertEquals(result.size(), 2);
			for (ArticleResponse response : result) {
				assertEquals(response.getUser().getName(), userName);
			}
		}
	}

	@Nested
	@DisplayName("[여행일지 등록]")
	class registerTest {
		@Test
		@DisplayName("[실패] - 존재하지 않는 userId일 때")
		void registerArticle_notFoundUserError() {
			//given
			final Integer userId = 5;
			doReturn(Optional.empty()).when(userRepository).findById(userId);

			//when
			final UserNotFoundException result = assertThrows(UserNotFoundException.class
				, () -> articleService.createArticle(ArticleRequest.builder().userId(userId).build())
			);

			//then
			assertEquals(USER_NOT_FOUND_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[실패] - 답변 리스트 개수가 3개 미만일때")
		void registerArticle_answersSizeIsNotEnoughError() {
			//given
			final Integer userId = 3;
			doReturn(Optional.of(User.builder().build())).when(userRepository).findById(userId);

			final List<AnswerRegisterRequest> answers = answerRegisterRequests(ANSWERS_MIN_SIZE - 1);

			//when
			final AnswersSizeIsNotEnoughException result = assertThrows(AnswersSizeIsNotEnoughException.class
				, () -> articleService.createArticle(ArticleRequest.builder().userId(userId).build())
			);

			//then
			assertEquals(ARTICLE_ANSWERS_SIZE_IS_NOT_ENOUGH_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[실패] - OPEN 가능한 질문이 아닌데 OPEN 상태로 온 답변이 있을 경우")
		void registerArticle_canNotBeOpenedAnswerError() {
			//given
			final Integer userId = 5;
			final String userName = "유저이름";
			doReturn(Optional.of(User.builder().build())).when(userRepository).findById(userId);

			final List<AnswerRegisterRequest> answers = new ArrayList<>();
			for (int i = 0; i < ANSWERS_MIN_SIZE; i++) {
				answers.add(AnswerRegisterRequest.builder()
					.questionId(1)
					.state(State.OPEN)
					.build());
			}

			final ArticleRequest request = ArticleRequest.builder()
				.userId(userId)
				.answers(answers)
				.createDate(LocalDateTime.now())
				.build();
			final Article article = Article.from(request, User.builder().id(userId).name(userName).build());
			doReturn(article).when(articleRepository).save(any(Article.class));

			//when
			final CanNotBeOpenedAnswerException result = assertThrows(CanNotBeOpenedAnswerException.class
				, () -> articleService.createArticle(request)
			);

			//then
			assertEquals(CAN_NOT_BE_OPENED_ANSWER_ERROR_MESSAGE, result.getMessage());
		}

		@Test
		@DisplayName("[성공]")
		void registerArticle_success() {
			//given
			final Integer userId = 5;
			final String userName = "유저이름";
			doReturn(Optional.of(User.builder().build())).when(userRepository).findById(userId);

			final ArticleRequest request = ArticleRequest.builder()
				.userId(userId)
				.answers(answerRegisterRequests(ANSWERS_MIN_SIZE))
				.createDate(LocalDateTime.now())
				.build();
			final Article article = Article.from(request, User.builder().id(userId).name(userName).build());
			doReturn(article).when(articleRepository).save(any(Article.class));

			//when
			articleService.createArticle(request);

			//then
			assertEquals(5, article.getUser().getId());
			assertEquals(userName, article.getUser().getName());
			assertEquals(ANSWERS_MIN_SIZE, article.getAnswers().size());
		}

		private List<AnswerRegisterRequest> answerRegisterRequests(final int answerSize) {
			final List<AnswerRegisterRequest> answers = new ArrayList<>();
			for (int i = 0; i < answerSize; i++) {
				answers.add(AnswerRegisterRequest.builder().questionId(1).state(State.CLOSE).build());
			}
			return answers;
		}
	}

}