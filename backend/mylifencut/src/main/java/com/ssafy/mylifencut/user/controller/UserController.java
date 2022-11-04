package com.ssafy.mylifencut.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<BaseResponse> kakaoLogin(@RequestBody String accessToken) {
		Integer userId = userService.kakaoLogin(accessToken);
		return new ResponseEntity<>(BaseResponse.from(true, "성공", userId), HttpStatus.OK);
	}
}
