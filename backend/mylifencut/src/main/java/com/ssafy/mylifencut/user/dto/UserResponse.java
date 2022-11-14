package com.ssafy.mylifencut.user.dto;

import com.ssafy.mylifencut.user.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
	private String accessToken;
	private Integer userId;
	private String name;
	private String email;
	private String profileImage;

	public static UserResponse of(String accessToken, User user) {
		return UserResponse.builder()
			.accessToken(accessToken)
			.userId(user.getId())
			.email(user.getEmail())
			.name(user.getName())
			.build();
	}
}
