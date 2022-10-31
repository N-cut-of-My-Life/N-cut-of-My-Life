package com.ssafy.mylifencut.answer.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.user.domain.User;

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
			.articles(Collections.emptyList())
			.email("test@email.com")
			.name("테스트")
			.build();

		final LocalDateTime nowTime = LocalDateTime.now();
		final Article article = Article.builder()
			.id(1)
			.user(user)
			.answers(Collections.emptyList())
			.createDate(nowTime)
			.build();

		final Answer answer = Answer.builder()
			.id(1)
			.article(article)
			.questionId(1)
			.contents("답변 내용")
			.state(State.CLOSE)
			.build();

		final Answer savedAnswer = answerRepository.save(answer);
		assertNotNull(savedAnswer);
		assertEquals(savedAnswer.getId(), 1);
		assertEquals(savedAnswer.getArticle(), article);
		assertEquals(savedAnswer.getQuestionId(), 1);
		assertEquals(savedAnswer.getContents(), "답변 내용");
		assertEquals(savedAnswer.getState(), State.CLOSE);
	}
}