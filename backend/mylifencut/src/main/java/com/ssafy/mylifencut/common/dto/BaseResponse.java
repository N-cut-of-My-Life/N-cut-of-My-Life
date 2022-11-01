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
	private boolean isSuccess;
	private String message;
	private Object data;

	public static BaseResponse from(boolean isSuccess, String message) {
		return builder().isSuccess(isSuccess).message(message).build();
	}

	public static BaseResponse from(boolean isSuccess, String message, Object data) {
		return builder().isSuccess(isSuccess).message(message).data(data).build();
	}
}
