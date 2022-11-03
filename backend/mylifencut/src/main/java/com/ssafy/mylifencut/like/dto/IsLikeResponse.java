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
	private final Integer user_id;
	private final Integer answer_id;

	public static IsLikeResponse of(IsLike isLike) {
		return IsLikeResponse.builder()
			.id(isLike.getId())
			.user_id(isLike.getUser().getId())
			.answer_id(isLike.getAnswer().getId())
			.build();
	}
}
