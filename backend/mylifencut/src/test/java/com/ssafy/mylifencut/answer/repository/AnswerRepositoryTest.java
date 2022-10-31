package com.ssafy.mylifencut.answer.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

@DataJpaTest
class AnswerRepositoryTest {
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("답변 등록 성공")
	public void createAnswer() {
		//given
		final User user = User.builder()
			.id(1)
			.email("test@email.com")
			.name("테스트")
			.build();

		final Answer answer = Answer.builder()
			.id(1)
			.user(user)
			.questionId(1)
			.contents("답변 내용")
			.state(State.CLOSE)
			.build();

		final Answer savedAnswer = answerRepository.save(answer);
		assertNotNull(savedAnswer);
		assertEquals(savedAnswer.getId(), 1);
		assertEquals(savedAnswer.getUser(), user);
		assertEquals(savedAnswer.getQuestionId(), 1);
		assertEquals(savedAnswer.getContents(), "답변 내용");
		assertEquals(savedAnswer.getState(), State.CLOSE);
	}
}