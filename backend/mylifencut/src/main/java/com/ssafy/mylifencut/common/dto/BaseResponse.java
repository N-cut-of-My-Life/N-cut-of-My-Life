package com.ssafy.mylifencut.common.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	// public static ResponseEntity<?> success(String message, Object data) {
	// 	return ResponseEntity.ok(new BaseResponse(true, message, data));
	// }
	//
	// public static ResponseEntity<?> success(String message) {
	// 	return ResponseEntity.ok(new BaseResponse(true, message));
	// }
	//
	// public static ResponseEntity<?> error(String message, HttpStatus httpStatus) {
	// 	return new ResponseEntity<>(new BaseResponse(false, message), httpStatus);
	// }

}
