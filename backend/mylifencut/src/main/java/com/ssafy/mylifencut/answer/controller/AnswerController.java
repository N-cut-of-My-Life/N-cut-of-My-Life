package com.ssafy.mylifencut.answer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.service.AnswerService;
import com.ssafy.mylifencut.common.dto.BaseResponse;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	private AnswerService answerService;
	@PostMapping
	public ResponseEntity<?> createAnswer(AnswerRequest answerRequest) {
		//answerService.register(answerRequest);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
