package com.ssafy.mylifencut.answer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRequest;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

	private final AnswerRepository answerRepository;

	public BaseResponse register(AnswerRequest answerRequest) {

		return BaseResponse.from(true, "답변이 등록되었습니다.");
	}

	@Transactional
	public Answer createAnswer(AnswerRequest answerRequest) {
		Answer result = answerRepository.save(answer);
		if (result.getQuestionId() != 9 && result.getState().equals(State.OPEN)) {
			throw new InvalidStateException(AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
		}
		return null;
	}
}
