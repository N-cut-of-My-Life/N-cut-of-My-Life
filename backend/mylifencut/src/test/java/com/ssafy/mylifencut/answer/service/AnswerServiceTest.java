package com.ssafy.mylifencut.answer.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.ssafy.mylifencut.answer.exception.GalleryNotFoundException;
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
	@DisplayName("[갤러리 단건 조회]")
	class GalleryReadOneTest {
		@Test
		@DisplayName("[실패] - 없는 갤러리 조회")
		void notFoundError() {
			// given
			final int userId = 1;
			final int galleryId = 1;
			doThrow(GalleryNotFoundException.class)
				.when(answerRepository)
				.findById(userId);

			// when

			// then
			assertThrows(GalleryNotFoundException.class, () -> answerService.getGalleryOne(userId, galleryId));
		}

		@Test
		@DisplayName("[성공] - 갤러리 조회(좋아요 있는 경우)")
		void myLikeIsIn() {
			// given
			final int userId = 1;
			final int answerId = 1;
			final LocalDateTime nowTime = LocalDateTime.now();
			final User user = User.builder()
				.id(userId)
				.articles(Collections.emptyList())
				.name("최주희")
				.build();
			final IsLike like = IsLike.builder()
				.answer(Answer.builder().id(1).build())
				.user(user).build();
			final List<IsLike> likes = Collections.singletonList(like);
			final Article article = Article.builder()
				.id(answerId)
				.user(user)
				.answers(Collections.emptyList())
				.createDate(nowTime)
				.build();
			final Answer answer = Answer.builder()
				.id(answerId)
				.article(article)
				.questionId(1)
				.contents("답변 내용")
				.state(State.CLOSE)
				.likes(likes)
				.build();
			GalleryResponse galleryResponse = GalleryResponse.of(answer);
			galleryResponse.setIsMine();

			doReturn(Optional.of(answer))
				.when(answerRepository)
				.findById(userId);
			doReturn(Optional.of(like))
				.when(likeRepository)
				.findByUserIdAndAnswerId(any(), any());

			// when
			GalleryResponse result = answerService.getGalleryOne(userId, answerId);

			// then
			assertEquals(galleryResponse.getAnswerId(), result.getAnswerId());
			assertEquals(galleryResponse.getLike(), result.getLike());
			assertEquals(galleryResponse.getContents(), result.getContents());
			assertEquals(galleryResponse.getUserId(), result.getUserId());
			assertEquals(galleryResponse.getImgUrl(), result.getImgUrl());
			assertEquals(galleryResponse.getIsMine(), result.getIsMine());
		}

		@Test
		@DisplayName("[성공] - 갤러리 조회(좋아요 없는 경우)")
		void myLikeIsNotIn() {
			// given
			final int userId = 1;
			final int answerId = 1;
			final LocalDateTime nowTime = LocalDateTime.now();
			final User user = User.builder()
				.id(userId)
				.articles(Collections.emptyList())
				.name("최주희")
				.build();
			final List<IsLike> likes = Collections.emptyList();
			final Article article = Article.builder()
				.id(answerId)
				.user(user)
				.answers(Collections.emptyList())
				.createDate(nowTime)
				.build();
			final Answer answer = Answer.builder()
				.id(answerId)
				.article(article)
				.questionId(1)
				.contents("답변 내용")
				.state(State.CLOSE)
				.likes(likes)
				.build();
			GalleryResponse galleryResponse = GalleryResponse.of(answer);

			doReturn(Optional.of(answer))
				.when(answerRepository)
				.findById(userId);
			doReturn(Optional.empty())
				.when(likeRepository)
				.findByUserIdAndAnswerId(userId, answerId);

			// when
			GalleryResponse result = answerService.getGalleryOne(userId, answerId);

			// then
			assertEquals(galleryResponse.getAnswerId(), result.getAnswerId());
			assertEquals(galleryResponse.getLike(), result.getLike());
			assertEquals(galleryResponse.getContents(), result.getContents());
			assertEquals(galleryResponse.getUserId(), result.getUserId());
			assertEquals(galleryResponse.getImgUrl(), result.getImgUrl());
			assertEquals(galleryResponse.getIsMine(), result.getIsMine());
		}
	}

	@Nested
	@DisplayName("[음악 조회]")
	class SearchMusicTest {

		@Test
		@DisplayName("[성공] - 음악조회")
		void searchMusic() throws Exception {
			//given
			String keyword = "가을타나봐";
			String expectedApiUrl = KeyWordConverterToURI.converter(keyword);
			List<MusicResponse> musicResponseList = answerService.searchMusic(expectedApiUrl);
			//then
			assertThat(musicResponseList).hasSize(10);
		}
	}
}