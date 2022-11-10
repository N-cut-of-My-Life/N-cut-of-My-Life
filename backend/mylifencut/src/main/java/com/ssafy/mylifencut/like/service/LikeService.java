package com.ssafy.mylifencut.like.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.dto.IsLikeResponse;
import com.ssafy.mylifencut.like.exception.AlreadyLikeException;
import com.ssafy.mylifencut.like.exception.NotExistLikeException;
import com.ssafy.mylifencut.like.repository.LikeRepository;
import com.ssafy.mylifencut.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

	private final LikeRepository likeRepository;

	@Transactional
	public IsLikeResponse createLike(Integer userId, Integer answerId) {
		final Optional<IsLike> result = likeRepository.findByUserIdAndAnswerId(userId, answerId);
		if (result.isPresent()) {
			throw new AlreadyLikeException();
		}
		final IsLike isLike = IsLike.builder()
			.user(User.builder().id(userId).build())
			.answer(Answer.builder().id(answerId).build())
			.build();
		final IsLike savedIsLike = likeRepository.save(isLike);
		return IsLikeResponse.of(savedIsLike);
	}

	@Transactional
	public void deleteLike(Integer userId, Integer answerId) {
		final IsLike isLike = likeRepository.findByUserIdAndAnswerId(userId, answerId).orElseThrow(
			NotExistLikeException::new);
		likeRepository.delete(isLike);
	}
}
