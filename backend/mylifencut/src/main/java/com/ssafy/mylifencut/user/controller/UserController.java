package com.ssafy.mylifencut.user.controller;

import static com.ssafy.mylifencut.user.UserConstant.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.dto.KakaoRequest;
import com.ssafy.mylifencut.user.dto.TokenRequest;
import com.ssafy.mylifencut.user.dto.TokenResponse;
import com.ssafy.mylifencut.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@PostMapping("/login")
	@ApiOperation(value = "카카오 로그인", notes = "카카오 AccessToken으로 로그인")
	public ResponseEntity<BaseResponse> kakaoLogin(@RequestBody KakaoRequest kakaoRequest) {
		String accessToken = kakaoRequest.getAccessToken();
		return new ResponseEntity<>(
			BaseResponse.from(true, KAKAO_LOGIN_SUCCESS_MESSAGE, userService.kakaoLogin(accessToken)), HttpStatus.OK);
	}

	@PostMapping("/token")
	@ApiOperation(value = "Refresh Token 갱신", notes = "Refresh, Access Token을 보내서 새로 갱신된 토큰 발급")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "토큰 재발급 성공", response = TokenResponse.class),
		@ApiResponse(code = 400, message = "토큰 오류로 재발급 실패")
	})
	public ResponseEntity<BaseResponse> reissueToken(@RequestBody TokenRequest tokenRequest) {
		return new ResponseEntity<>(
			BaseResponse.from(true, TOKEN_REISSUE_SUCCESS_MESSAGE, userService.reissueToken(tokenRequest)),
			HttpStatus.OK);
	}
}
