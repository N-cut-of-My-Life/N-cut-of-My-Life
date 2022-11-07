package com.ssafy.mylifencut.answer.exception;

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.common.exception.BadRequestException;

public class InvalidStateException extends BadRequestException {

	public InvalidStateException() {
		super(AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
	}
}
