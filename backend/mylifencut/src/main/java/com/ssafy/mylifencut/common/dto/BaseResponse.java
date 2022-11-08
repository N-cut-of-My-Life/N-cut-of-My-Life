package com.ssafy.mylifencut.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor
public class BaseResponse {
	private boolean success;
	private String message;
	private Object data;

	public static BaseResponse from(boolean success, String message) {
		return builder().success(success).message(message).build();
	}

	public static BaseResponse from(boolean success, String message, Object data) {
		return builder().success(success).message(message).data(data).build();
	}
}
