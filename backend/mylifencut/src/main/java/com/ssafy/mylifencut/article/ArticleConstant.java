package com.ssafy.mylifencut.article;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleConstant {
	public static final int ANSWERS_MIN_SIZE = 4;
	public static final String ARTICLE_ANSWERS_SIZE_IS_NOT_ENOUGH_ERROR_MESSAGE
		= "답변 개수가" + ANSWERS_MIN_SIZE + "개 이상이어야합니다.";
	public static final String RETRIEVE_SUCCESS_MESSAGE = "여행일지 조회에 성공하였습니다.";
	public static final String REGISTER_SUCCESS_MESSAGE = "여행일지를 등록하였습니다.";
}
