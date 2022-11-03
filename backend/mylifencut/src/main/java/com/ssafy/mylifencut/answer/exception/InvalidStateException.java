package com.ssafy.mylifencut.answer.exception;

import com.ssafy.mylifencut.answer.AnswerConstant;

public class InvalidStateException extends RuntimeException {

	public InvalidStateException() {
		super(AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
	}
}
