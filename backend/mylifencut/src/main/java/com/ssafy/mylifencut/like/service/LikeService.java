package com.ssafy.mylifencut.like.service;

import org.springframework.stereotype.Service;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.LikeErrorResult;
import com.ssafy.mylifencut.like.exception.alreadyLikeException;
import com.ssafy.mylifencut.like.repository.LikeRepository;
import com.ssafy.mylifencut.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {

	private final LikeRepository likeRepository;

	public IsLikeResponse createLike(Integer userId, Integer answerId) {
		final IsLike result = likeRepository.findByUserIdAndAnswerId(userId, answerId);
		if (result != null) {
			throw new alreadyLikeException(LikeErrorResult.ALREADY_LIKE);
		}
		final IsLike isLike = IsLike.builder()
			.user(User.builder().id(userId).build())
			.answer(Answer.builder().id(answerId).build())
			.build();

		final IsLike savedIsLike = likeRepository.save(isLike);
		IsLikeResponse isLikeResponse = IsLikeResponse.of(savedIsLike);

		return isLikeResponse;
	}
}
