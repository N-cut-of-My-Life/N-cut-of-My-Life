package com.ssafy.mylifencut.user.controller;

import static com.ssafy.mylifencut.user.UserConstant.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.user.dto.KakaoRequest;
import com.ssafy.mylifencut.user.dto.Token;
import com.ssafy.mylifencut.user.exception.InvalidAccessTokenException;
import com.ssafy.mylifencut.user.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private static final int COOKIE_EXPIRE_TIME = 60 * 60 * 24 * 14;    // 쿠키 14일 유지

	private final UserService userService;

	@GetMapping("/exception")
	@ApiOperation(value = "Header에 JWT가 없을 경우")
	public void noAccessToken() {
		throw new InvalidAccessTokenException();
	}

	@PostMapping("/login")
	@ApiOperation(value = "카카오 로그인", notes = "카카오 AccessToken으로 로그인")
	public ResponseEntity<BaseResponse> kakaoLogin(@RequestBody KakaoRequest kakaoRequest,
		HttpServletResponse response) {
		String accessToken = kakaoRequest.getAccessToken();
		Token token = userService.kakaoLogin(accessToken);
		return getUserInfo(response, token, KAKAO_LOGIN_SUCCESS_MESSAGE);
	}

	@GetMapping("/token")
	@ApiOperation(value = "Token 갱신", notes = "Refresh, Access Token을 보내서 새로 갱신된 토큰 발급")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "토큰 재발급 성공", response = Token.class),
		@ApiResponse(code = 400, message = "토큰 오류로 재발급 실패")
	})
	public ResponseEntity<BaseResponse> reissueToken(
		@CookieValue(value = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
		Token token = userService.reissueToken(refreshToken);

		return getUserInfo(response, token, TOKEN_REISSUE_SUCCESS_MESSAGE);
	}

	private ResponseEntity<BaseResponse> getUserInfo(HttpServletResponse response, Token token,
		String message) {
		Cookie cookie = new Cookie("refreshToken", token.getRefreshToken());

		cookie.setSecure(true);
		cookie.setHttpOnly(true);
		cookie.setPath("/");

		cookie.setMaxAge(COOKIE_EXPIRE_TIME);

		response.addCookie(cookie);

		return new ResponseEntity<>(
			BaseResponse.from(true, message, userService.getUserResponse(token.getAccessToken())),
			HttpStatus.OK);
	}
}
