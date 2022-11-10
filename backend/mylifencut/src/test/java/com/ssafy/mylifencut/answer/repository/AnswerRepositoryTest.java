package com.ssafy.mylifencut.answer.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

@DataJpaTest
@DisplayName("답변 Repository Test")
class AnswerRepositoryTest {
	@Autowired
	private AnswerRepository answerRepository;

	@Nested
	@DisplayName("[갤러리 조회]")
	class GalleryReadFailTest{
		@Test
		@DisplayName("[실패] - 등록된 답변이 없음")
		void answerListSizeZero() {
			//given
			//when
			List<Answer> result = answerRepository.findAll();
			//then
			assertThat(result).isEmpty();
		}

		@Test
		@DisplayName("[실패] - 등록된 답변의 상태가 OPEN인 답변이 없음")
		void notExistAnswerStateOpen() {
			//given
			//when
			List<Answer> result = answerRepository.findAllByState(State.OPEN);
			//then
			assertThat(result).isEmpty();
		}

		@Test
		@DisplayName("[성공] - 갤러리 조회")
		void readGallery() {
			//given
			final Answer answer = Answer.builder()
				.questionId(9)
				.contents("답변 내용")
				.state(State.OPEN)
				.build();
			final Answer answer2 = Answer.builder()
				.questionId(9)
				.contents("답변 내용입니다.")
				.state(State.OPEN)
				.build();
			final Answer answer3 = Answer.builder()
				.questionId(3)
				.contents("답변 내용이지롱.")
				.state(State.CLOSE)
				.build();
			answerRepository.save(answer);
			answerRepository.save(answer2);
			answerRepository.save(answer3);
			//when
			List<Answer> result = answerRepository.findAllByState(State.OPEN);
			//then
			assertThat(result).hasSize(2);
		}

	}

}