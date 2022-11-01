package com.ssafy.mylifencut.answer.controller;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

	private final AnswerService answerService;

	@PostMapping
	public ResponseEntity<?> createAnswer(@RequestBody final AnswerRequest answerRequest) {
		return new ResponseEntity<>(
			BaseResponse.from(true, CREATE_ANSWER_SUCCESS_MESSAGE, answerService.createAnswer(answerRequest)),
			HttpStatus.OK);
	}
}
