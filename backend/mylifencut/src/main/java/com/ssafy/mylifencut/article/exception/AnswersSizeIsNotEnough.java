package com.ssafy.mylifencut.article.exception;

import com.ssafy.mylifencut.article.ArticleConstant;

public class AnswersSizeIsNotEnough extends RuntimeException {
	public AnswersSizeIsNotEnough() {
		super(ArticleConstant.ARTICLE_ANSWERS_SIZE_IS_NOT_ENOUGH_ERROR_MESSAGE);
	}
}
