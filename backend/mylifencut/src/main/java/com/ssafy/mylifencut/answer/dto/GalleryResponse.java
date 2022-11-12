package com.ssafy.mylifencut.answer.dto;

import com.ssafy.mylifencut.answer.domain.Answer;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GalleryResponse {
	private int id;
	private int userId;
	private int answerId;
	private String contents;
	private Integer like;

	public static GalleryResponse of(Answer answer) {
		return GalleryResponse.builder()
				.id(answer.getId()).answerId(answer.getId())
			.userId(answer.getArticle().getUser().getId())
			.contents(answer.getContents())
			.like(answer.getLikes().size())
			.build();
	}
}
