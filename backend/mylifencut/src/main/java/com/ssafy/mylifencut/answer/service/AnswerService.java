package com.ssafy.mylifencut.answer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;
import com.ssafy.mylifencut.answer.dto.AnswerResponse;
import com.ssafy.mylifencut.answer.exception.InvalidStateException;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

	private final AnswerRepository answerRepository;

	public BaseResponse register(AnswerRegisterRequest answerRegisterRequest) {

		return BaseResponse.from(true, "답변이 등록되었습니다.");
	}

	@Transactional
	public AnswerResponse createAnswer(AnswerRegisterRequest answerRegisterRequest) {
		Answer result = answerRepository.save(Answer.from(answerRegisterRequest, Article.builder().build()));
		AnswerResponse answerResponse = AnswerResponse.of(result);
		if (result.getQuestionId() != 9 && result.getState().equals(State.OPEN)) {
			throw new InvalidStateException();
		}
		return answerResponse;
	}
}
