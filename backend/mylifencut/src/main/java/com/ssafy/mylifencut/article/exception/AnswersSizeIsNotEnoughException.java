package com.ssafy.mylifencut.article.exception;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.common.exception.BadRequestException;

public class AnswersSizeIsNotEnoughException extends BadRequestException {
	public AnswersSizeIsNotEnoughException() {
		super(ArticleConstant.ARTICLE_ANSWERS_SIZE_IS_NOT_ENOUGH_ERROR_MESSAGE);
	}
}
