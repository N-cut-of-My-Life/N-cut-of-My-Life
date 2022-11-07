package com.ssafy.mylifencut.answer.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.dto.AnswerResponse;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.user.domain.User;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

	@InjectMocks
	private AnswerService answerService;
	@Mock
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("답변등록 - 공개여부를 선택할 수 없는 답변의 상태가 OPEN일 때")
	public void invalidState() {
		//given
		final Answer invalidAnswer = Answer.builder()
			.id(0)
			.questionId(1)
			.contents("잘못됨")
			.state(State.OPEN)
			.article(Article.builder().build())
			.build();

		doReturn(invalidAnswer).when(answerRepository).save(any(Answer.class));
		//when
		final InvalidStateException result = assertThrows(InvalidStateException.class, () -> answerService.createAnswer(
			new AnswerRegisterRequest(1, invalidAnswer.getQuestionId(), invalidAnswer.getContents(),
				invalidAnswer.getState())));
		//then
		assertEquals(result.getMessage(), AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("답변등록 - 성공")
	public void createAnswer() {
		//given
		Answer answer = newAnswer();
		AnswerResponse answerResponse = AnswerResponse.of(answer);
		doReturn(answer).when(answerRepository).save(any(Answer.class));

		//when
		AnswerRegisterRequest answerRegisterRequest = new AnswerRegisterRequest(0, answer.getQuestionId(),
			answer.getContents(),
			answer.getState());
		final AnswerResponse result = answerService.createAnswer(answerRegisterRequest);

		//then
		assertNotNull(result);
		assertEquals(answerResponse.getId(), result.getId());
		assertEquals(answerResponse.getArticleId(), result.getArticleId());
		assertEquals(answerResponse.getQuestionId(), result.getQuestionId());
		assertEquals(answerResponse.getContents(), result.getContents());
		assertEquals(answerResponse.getState(), result.getState());
	}


	@Nested
	@DisplayName("갤러리 조회 테스트")
	class GalleryReadTest{
		@Test
		@DisplayName("갤러리 조회 - 성공")
		public void readGallery() {
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





	private Answer newAnswer() {

		return Answer.builder()
			.id(1)
			.article(Article.builder().build())
			.contents("답변 내용")
			.state(State.CLOSE)
			.questionId(2)
			.build();
	}

}