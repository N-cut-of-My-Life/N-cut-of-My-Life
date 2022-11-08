package com.ssafy.mylifencut.answer.exception;

import com.ssafy.mylifencut.answer.AnswerConstant;
import com.ssafy.mylifencut.common.exception.BadRequestException;

public class CanNotBeOpenedAnswerException extends BadRequestException {

	public CanNotBeOpenedAnswerException() {
		super(AnswerConstant.CAN_NOT_BE_OPENED_ANSWER_ERROR_MESSAGE);
	}
}
