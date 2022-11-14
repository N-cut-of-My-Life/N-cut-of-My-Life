package com.ssafy.mylifencut.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserInfo {
	private String email;
	private String name;
	private String profileImage;
}
