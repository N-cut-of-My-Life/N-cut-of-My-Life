package com.ssafy.mylifencut.like.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.like.LikeConstant;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;
import com.ssafy.mylifencut.like.repository.LikeRepository;
import com.ssafy.mylifencut.user.domain.User;

@ExtendWith(MockitoExtension.class)
@DisplayName("좋아요 서비스 테스트")
@Nested
class LikeServiceTest {

	@InjectMocks
	private LikeService likeService;
	@Mock
	private LikeRepository likeRepository;

	private final Integer userId = 1;
	private final Integer answerId = 3;

	@Nested
	@DisplayName("[좋아요 추가]")
	class RegisterLikeTest{
		@Test
		@DisplayName("[실패] - 같은 답변에 대해 좋아요가 이미 선택되어있음")
		void alreadyLike() {

			IsLike isLike = IsLike.builder().build();
			//given
			doReturn(Optional.of(isLike)).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
			//when
			final AlreadyLikeException result = assertThrows(AlreadyLikeException.class,
				() -> likeService.createLike(userId, answerId));
			//then
			assertThat(result.getMessage()).isEqualTo(LikeConstant.ALREADY_LIKE_EXIST_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("[성공] - 좋아요 추가")
		void addLike() {
			IsLike.from();
			//given
			doReturn(Optional.empty()).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
			doReturn(isLike()).when(likeRepository).save(any(IsLike.class));
			//when
			final IsLikeResponse result = likeService.createLike(userId, answerId);
			//then
			assertThat(result.getId()).isNotNull();
			assertThat(result.getUserId()).isEqualTo(1);
			assertThat(result.getAnswerId()).isEqualTo(3);
		}
	}

	@Nested
	@DisplayName("[좋아요 삭제]")
	class DeleteLikeTest{
		@Test
		@DisplayName("[실패] - 좋아요를 누르지 않았음")
		void notExistLike() {
			//given
			doReturn(Optional.empty()).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
			//when
			final NotExistLikeException result = assertThrows(NotExistLikeException.class,
				() -> likeService.deleteLike(userId, answerId));
			//then
			assertThat(result.getMessage()).isEqualTo(LikeConstant.NOT_EXIST_LIKE_ERROR_MESSAGE);
		}

		@Test
		@DisplayName("[성공] - 좋아요 삭제")
		void deleteLike() {
			//given
			final IsLike isLike = isLike();
			doReturn(Optional.of(isLike)).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
			//when
			likeService.deleteLike(userId, answerId);
			//then
		}
	}



	private IsLike isLike() {
		return IsLike.builder()
			.id(1)
			.user(User.builder().id(userId).build())
			.answer(Answer.builder().id(answerId).build())
			.build();
	}
}