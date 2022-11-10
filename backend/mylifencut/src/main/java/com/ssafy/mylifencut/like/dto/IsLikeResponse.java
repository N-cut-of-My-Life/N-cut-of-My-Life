package com.ssafy.mylifencut.like.dto;

import com.ssafy.mylifencut.like.domain.IsLike;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class IsLikeResponse {

	private final Integer id;
	private final Integer userId;
	private final Integer answerId;

	public static IsLikeResponse of(IsLike isLike) {
		return IsLikeResponse.builder()
			.id(isLike.getId())
			.userId(isLike.getUser().getId())
			.answerId(isLike.getAnswer().getId())
			.build();
	}
}
