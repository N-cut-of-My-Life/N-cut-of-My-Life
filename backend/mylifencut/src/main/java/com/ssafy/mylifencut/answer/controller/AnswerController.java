package com.ssafy.mylifencut.answer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.service.AnswerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

	private final AnswerService answerService;
	@PostMapping
	public ResponseEntity<?> createAnswer(@RequestBody final AnswerRequest answerRequest) {
		return new ResponseEntity<>(answerService.createAnswer(answerRequest), HttpStatus.OK);
	}
}
