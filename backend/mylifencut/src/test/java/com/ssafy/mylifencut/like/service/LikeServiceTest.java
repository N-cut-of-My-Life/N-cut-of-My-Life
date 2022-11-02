package com.ssafy.mylifencut.like.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.LikeErrorResult;
import com.ssafy.mylifencut.like.exception.alreadyLikeException;
import com.ssafy.mylifencut.like.repository.LikeRepository;
import com.ssafy.mylifencut.user.domain.User;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

	@InjectMocks
	private LikeService likeService;
	@Mock
	private LikeRepository likeRepository;

	private final Integer userId = 1;
	private final Integer answerId = 3;

	@Test
	@DisplayName("좋아요 추가 실패 - 같은 답변에 대해 좋아요가 이미 선택되어있음")
	public void alreadyLike() {
		//given
		doReturn(IsLike.builder().build()).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
		//when
		final alreadyLikeException result = assertThrows(alreadyLikeException.class,
			() -> likeService.createLike(userId, answerId));
		//then
		assertThat(result.getErrorResult()).isEqualTo(LikeErrorResult.ALREADY_LIKE);
	}

	@Test
	@DisplayName("좋아요 추가 성공")
	public void addLike() {
		//given
		doReturn(null).when(likeRepository).findByUserIdAndAnswerId(userId, answerId);
		doReturn(isLike()).when(likeRepository).save(any(IsLike.class));
		//when
		final IsLikeResponse result = likeService.createLike(userId, answerId);
		//then
		assertThat(result.getId()).isNotNull();
		assertThat(result.getUser_id()).isEqualTo(1);
		assertThat(result.getAnswer_id()).isEqualTo(3);
	}

	private IsLike isLike() {
		return IsLike.builder()
			.Id(1)
			.user(User.builder().id(userId).build())
			.answer(Answer.builder().id(answerId).build())
			.build();
	}

}