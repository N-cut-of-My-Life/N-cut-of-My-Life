package com.ssafy.mylifencut.answer.controller;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;
import static com.ssafy.mylifencut.like.LikeConstant.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.answer.util.KeyWordConverterToURI;
import com.ssafy.mylifencut.common.dto.BaseResponse;
import com.ssafy.mylifencut.like.service.LikeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

	private final AnswerService answerService;
	private final LikeService likeService;

	@ApiImplicitParams({
		@ApiImplicitParam(
			name = "X-AUTH-TOKEN",
			value = "AccessToken",
			required = true, dataType = "String", paramType = "header")
	})
	@Operation(summary = "좋아요 추가", description = "답변 번호(answerId)와 유저 아이디(userId)를 이용하여 답변에 좋아요를 추가합니다.")
	@PostMapping("/{answerId}/{userId}")
	public ResponseEntity<BaseResponse> registerLike(@PathVariable("answerId") Integer answerId,
		@PathVariable("userId") Integer userId) {
		return new ResponseEntity<>(
			BaseResponse.from(true, CREATE_LIKE_SUCCESS_MESSAGE, likeService.createLike(userId, answerId)),
			HttpStatus.OK);
	}

	@ApiImplicitParams({
		@ApiImplicitParam(
			name = "X-AUTH-TOKEN",
			value = "AccessToken",
			required = true, dataType = "String", paramType = "header")
	})
	@Operation(summary = "좋아요 삭제", description = "답변 번호(answerId)와 유저 아이디(userId)를 이용하여 답변에 좋아요를 삭제합니다.")
	@DeleteMapping("/{answerId}/{userId}")
	public ResponseEntity<BaseResponse> deleteLike(@PathVariable("answerId") Integer answerId,
		@PathVariable("userId") Integer userId) {
		likeService.deleteLike(userId, answerId);
		return new ResponseEntity<>(
			BaseResponse.from(true, DELETE_LIKE_SUCCESS_MESSAGE),
			HttpStatus.NO_CONTENT);
	}

	@ApiImplicitParams({
		@ApiImplicitParam(
			name = "X-AUTH-TOKEN",
			value = "AccessToken",
			required = true, dataType = "String", paramType = "header")
	})
	@Operation(summary = "갤러리 조회", description = "STATE 상태가 OPEN 인 답변을 조회합니다.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "갤러리 조회 성공", response = GalleryResponse.class),
		@ApiResponse(code = 400, message = "갤러리 조회 실패")
	})
	@GetMapping("/{userId}")
	public ResponseEntity<BaseResponse> readGallery(@PathVariable("userId") Integer userId) {

		return new ResponseEntity<>(
			BaseResponse.from(true, READ_GALLERY_SUCCESS_MESSAGE, answerService.getGalleryList(userId)),
			HttpStatus.OK);
	}


	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "X-AUTH-TOKEN",
					value = "AccessToken",
					required = true, dataType = "String", paramType = "header")
	})
	@Operation(summary = "갤러리 단건 조회", description = "답변 번호(answerId)와 유저 아이디(userId)를 이용하여 공개여부(STATE)가 OPEN 인 답변을 조회합니다. ")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "갤러리 단건 조회 성공", response = GalleryResponse.class),
			@ApiResponse(code = 400, message = "갤러리 단건 조회 실패")
	})
	@GetMapping("/")
	public ResponseEntity<BaseResponse> readGalleryOne(
		@RequestParam(name = "userId") Integer userId,
		@RequestParam(name = "answerId") Integer answerId) {

		return new ResponseEntity<>(
			BaseResponse.from(true, READ_GALLERY_SUCCESS_MESSAGE, answerService.getGalleryOne(userId, answerId)),
			HttpStatus.OK
		);
	}

	@ApiImplicitParams({
			@ApiImplicitParam(
					name = "X-AUTH-TOKEN",
					value = "AccessToken",
					required = true, dataType = "String", paramType = "header")
	})
	@Operation(summary = "음악 검색", description = "검색어(keyword)를 이용해 노래 리스트를 검색합니다.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "음악 검색 성공"),
			@ApiResponse(code = 400, message = "음악 검색 실패")
	})
	@GetMapping("music/{keyword}")
	public ResponseEntity<BaseResponse> searchMusic(@PathVariable("keyword") String keyword) throws Exception {
		return new ResponseEntity<>(
			BaseResponse.from(true, SEARCH_MUSIC_SUCCESS_MESSAGE,
				answerService.searchMusic(KeyWordConverterToURI.converter(keyword))
			), HttpStatus.OK);

	}
}
