package com.ssafy.mylifencut.answer.controller;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;
import static com.ssafy.mylifencut.like.LikeConstant.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.service.LikeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

	private final AnswerService answerService;
	private final LikeService likeService;

	@PostMapping
	public ResponseEntity<BaseResponse> registerAnswer(@RequestBody final AnswerRegisterRequest answerRegisterRequest) {
		return new ResponseEntity<>(
			BaseResponse.from(true, CREATE_ANSWER_SUCCESS_MESSAGE, answerService.createAnswer(answerRegisterRequest)),
			HttpStatus.OK);
	}

	@PostMapping("/{answerId}/{userId}")
	public ResponseEntity<BaseResponse> registerLike(@PathVariable("answerId") Integer answerId,
		@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(
			BaseResponse.from(true, CREATE_LIKE_SUCCESS_MESSAGE, likeService.createLike(userId, answerId)),
			HttpStatus.OK);
	}
}
