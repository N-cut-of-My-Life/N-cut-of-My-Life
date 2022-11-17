package com.ssafy.mylifencut.answer.exception;

import static com.ssafy.mylifencut.answer.AnswerConstant.*;

import com.ssafy.mylifencut.common.exception.BadRequestException;

public class GalleryNotFoundException extends BadRequestException {
	public GalleryNotFoundException() {
		super(GALLERY_NOT_FOUND_ERROR_MESSAGE);
	}
}
