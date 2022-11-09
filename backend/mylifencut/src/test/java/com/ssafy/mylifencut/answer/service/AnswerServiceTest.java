package com.ssafy.mylifencut.answer.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.user.domain.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("답변 service 테스트 코드")
class AnswerServiceTest {

	@InjectMocks
	private AnswerService answerService;
	@Mock
	private AnswerRepository answerRepository;

	@Nested
	@DisplayName("갤러리 조회 테스트")
	class GalleryReadTest{
		@Test
		@DisplayName("갤러리 조회 - 성공")
		void readGallery() {
			//given
			final LocalDateTime nowTime = LocalDateTime.now();
			final User user = User.builder()
				.id(1)
				.articles(Collections.emptyList())
				.name("최주희")
				.build();
			final User user1 = User.builder()
				.id(2)
				.articles(Collections.emptyList())
					.name("유일권")
					.build();
			final Article article = Article.builder()
					.user(user)
					.answers(Collections.emptyList())
					.createDate(nowTime)
					.build();
			final Article article2 = Article.builder()
					.user(user1)
					.answers(Collections.emptyList())
					.createDate(nowTime)
					.build();
			final List<IsLike> likes = new ArrayList<>();
			likes.add(new IsLike());
			likes.add(new IsLike());
			final Answer answer = Answer.builder()
					.article(article)
					.questionId(1)
					.contents("답변 내용")
					.state(State.CLOSE)
					.likes(likes)
					.build();
			doReturn(Arrays.asList(
					Answer.builder().id(1).article(article).contents("답변 내용").likes(likes).state(State.OPEN).build(),
					Answer.builder().id(2).article(article2).contents("답변 내용").likes(likes).state(State.OPEN).build()
			)).when(answerRepository).findAllByState(State.OPEN);
			//when
			final List<GalleryResponse> result = answerService.getGalleryList();
			//then
			assertThat(result.size()).isEqualTo(2);
		}
	}
}