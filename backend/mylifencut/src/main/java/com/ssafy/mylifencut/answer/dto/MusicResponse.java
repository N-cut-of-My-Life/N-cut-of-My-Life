package com.ssafy.mylifencut.answer.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MusicResponse {
	private String title;
	private String img;
	private String artist;

	public static MusicResponse of(String title, String img, String artist) {
		return MusicResponse.builder()
			.title(title)
			.img(img)
			.artist(artist)
			.build();
	}
}
