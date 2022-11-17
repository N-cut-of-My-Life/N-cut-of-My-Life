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
import com.ssafy.mylifencut.answer.dto.MusicResponse;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.answer.util.KeyWordConverterToURI;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.repository.LikeRepository;
import com.ssafy.mylifencut.user.domain.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("답변 서비스 test")
class AnswerServiceTest {

	@InjectMocks
	private AnswerService answerService;
	@Mock
	private AnswerRepository answerRepository;

	@Mock
	private LikeRepository likeRepository;

	@Nested
	@DisplayName("[갤러리 조회]")
	class GalleryReadTest {
		@Test
		@DisplayName("[성공] - 갤러리 조회")
		void readGallery() {
			//given
			Integer userId = 3;
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
			final IsLike like1 = IsLike.builder()
				.answer(Answer.builder().id(1).build())
				.user(user1).build();
			final IsLike like2 = IsLike.builder()
				.answer(Answer.builder().id(2).build())
				.user(user).build();
			likes.add(like1);
			likes.add(like2);
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
			final List<GalleryResponse> result = answerService.getGalleryList(userId);
			//then
			assertThat(result).hasSize(2);
		}
	}

	@Nested
	@DisplayName("[음악 조회]")
	class SearchMusicTest {

		/*@Mock
		private MockRestServiceServer mockServer;*/

		@Test
		@DisplayName("[성공] - 음악조회")
		void searchMusic() throws Exception {
			//given
			String keyword = "가을타나봐";
			String expectedApiUrl = KeyWordConverterToURI.converter(keyword);
		/*	mockServer
				.expect(requestTo(expectedApiUrl))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(SEARCH_MUSIC_RESPONSE, MediaType.APPLICATION_ATOM_XML));*/
			//when
			List<MusicResponse> musicResponseList = answerService.searchMusic(expectedApiUrl);
			//then
			assertThat(musicResponseList).hasSize(10);
		}
	}
}