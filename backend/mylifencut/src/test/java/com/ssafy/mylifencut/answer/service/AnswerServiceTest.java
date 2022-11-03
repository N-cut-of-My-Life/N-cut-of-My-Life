package com.ssafy.mylifencut.answer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.article.domain.Article;

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
		AnswerRegisterResponse answerRegisterResponse = AnswerRegisterResponse.of(answer);
		doReturn(answer).when(answerRepository).save(any(Answer.class));

		//when
		AnswerRegisterRequest answerRegisterRequest = new AnswerRegisterRequest(0, answer.getQuestionId(),
			answer.getContents(),
			answer.getState());
		final AnswerRegisterResponse result = answerService.createAnswer(answerRegisterRequest);

		//then
		assertNotNull(result);
		assertEquals(answerRegisterResponse.getId(), result.getId());
		assertEquals(answerRegisterResponse.getArticleId(), result.getArticleId());
		assertEquals(answerRegisterResponse.getQuestionId(), result.getQuestionId());
		assertEquals(answerRegisterResponse.getContents(), result.getContents());
		assertEquals(answerRegisterResponse.getState(), result.getState());
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