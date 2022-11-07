package com.ssafy.mylifencut.answer.controller;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;
import static com.ssafy.mylifencut.like.LikeConstant.*;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Operation(summary = "좋아요 추가", description = "답변 번호(answerId)와 유저 아이디(userId)를 이용하여 답변에 좋아요를 추가합니다.")
	@PostMapping("/{answerId}/{userId}")
	public ResponseEntity<BaseResponse> registerLike(@PathVariable("answerId") Integer answerId,
		@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(
			BaseResponse.from(true, CREATE_LIKE_SUCCESS_MESSAGE, likeService.createLike(userId, answerId)),
			HttpStatus.OK);
	}

	@Operation(summary = "좋아요 삭제", description = "답변 번호(answerId)와 유저 아이디(userId)를 이용하여 답변에 좋아요를 삭제합니다.")
	@DeleteMapping("/{answerId}/{userId}")
	public ResponseEntity<BaseResponse> deleteLike(@PathVariable("answerId") Integer answerId,
		@PathVariable("userId") Integer userId) {
		likeService.deleteLike(userId, answerId);
		return new ResponseEntity<>(
			BaseResponse.from(true, DELETE_LIKE_SUCCESS_MESSAGE),
			HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "갤러리 조회", description = "STATE 상태가 OPEN 인 답변을 조회합니다.")
	@GetMapping
	public ResponseEntity<BaseResponse> readGallery() {

		return new ResponseEntity<>(
			BaseResponse.from(true, READ_GALLERY_SUCCESS_MESSAGE,answerService.getGalleryList()),
			HttpStatus.OK);
	}

}
