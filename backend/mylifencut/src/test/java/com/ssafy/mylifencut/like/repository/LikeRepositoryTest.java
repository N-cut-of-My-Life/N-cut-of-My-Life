package com.ssafy.mylifencut.like.repository;

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
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
class LikeRepositoryTest {

	@Autowired
	private LikeRepository likeRepository;

	@Test
	@DisplayName("좋아요 추가 테스트")
	public void CreateLike() {
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
			.questionId(1)
			.contents("답변 내용")
			.state(State.CLOSE)
			.build();

		final IsLike like = IsLike.builder()
			.answer(answer)
			.build();
		//when
		final IsLike result = likeRepository.save(like);
		//then
		assertNotNull(result);
		assertEquals(result.getId(), like.getId());
		assertEquals(result.getAnswer(), answer);
	}

}