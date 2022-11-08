package com.ssafy.mylifencut.answer.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
@DisplayName("답변 Repository Test")
class AnswerRepositoryTest {
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("답변 등록 성공")
	@Disabled
	public void createAnswer() {
		//given
		final User user = User.builder()
			.articles(Collections.emptyList())
			.email("test@email.com")
			.name("테스트")
			.build();

		final LocalDateTime nowTime = LocalDateTime.now();
		final Article article = Article.builder()
			.user(user)
			.answers(Collections.emptyList())
			.createDate(nowTime)
			.build();

		final Answer answer = Answer.builder()
			.article(article)
			.imgUrl("/dir/img")
			.questionId(1)
			.contents("답변 내용")
			.state(State.CLOSE)
			.build();

		final Answer savedAnswer = answerRepository.save(answer);
		assertNotNull(savedAnswer);
		assertEquals(savedAnswer.getArticle(), article);
		assertEquals(savedAnswer.getQuestionId(), 1);
		assertEquals(savedAnswer.getContents(), "답변 내용");
		assertEquals(savedAnswer.getState(), State.CLOSE);
		assertEquals(savedAnswer.getImgUrl(),"/dir/img");
	}

	@Nested
	@DisplayName("갤러리 조회 실패 테스트")
	class GalleryReadFailTest{
		@Test
		@DisplayName("갤러리 조회 실패 - 등록된 답변이 없음")
		public void answerListSizeZero() {
			//given
			//when
			List<Answer> result = answerRepository.findAll();
			//then
			assertThat(result.size()).isEqualTo(0);
		}

		@Test
		@DisplayName("갤러리 조회 실패 - 등록된 답변의 상태가 OPEN인 답변이 없음")
		public void notExistAnswerStateOpen() {
			//given
			//when
			List<Answer> result = answerRepository.findAllByState(State.OPEN);
			//then
			assertThat(result.size()).isEqualTo(0);
		}

	}

	@Nested
	@DisplayName("갤러리 조회 성공 테스트")
	class GalleryReadSuccessTest{
		@Test
		@DisplayName("갤러리 조회 성공 ")
		public void readGallery() {
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
			assertThat(result.size()).isEqualTo(2);
		}
	}
}