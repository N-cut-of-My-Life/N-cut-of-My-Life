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
import com.ssafy.mylifencut.answer.dto.AnswerRequest;
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
		doReturn(Answer.builder().build()).when(answerRepository).save(any(Answer.class));
		//when
		final InvalidStateException result = assertThrows(InvalidStateException.class,
			() -> answerService.createAnswer(any()));
		//then
		assertEquals(result.getMessage(), AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
	}

	@Test
	@DisplayName("답변등록 - 성공")
	public void createAnswer() {
		//given
		Answer answer = newAnswer();
		doReturn(answer).when(answerRepository).save(any(Answer.class));

		//when
		AnswerRequest answerRequest = new AnswerRequest(0, answer.getQuestionId(), answer.getContents(),
			answer.getState());
		final Answer result = answerService.createAnswer(answerRequest);

		//then
		assertNotNull(result);
		assertEquals(answer.getId(), result.getId());
		assertEquals(answer.getArticle(), result.getArticle());
		assertEquals(answer.getQuestionId(), result.getQuestionId());
		assertEquals(answer.getContents(), result.getContents());
		assertEquals(answer.getState(), result.getState());

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