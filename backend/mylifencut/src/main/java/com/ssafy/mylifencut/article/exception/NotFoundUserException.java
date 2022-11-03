package com.ssafy.mylifencut.article.exception;

import com.ssafy.mylifencut.article.ArticleConstant;

public class NotFoundUserException extends RuntimeException {

	public NotFoundUserException() {
		super(ArticleConstant.NOT_FOUND_USER_ERROR_MESSAGE);
	}
}
