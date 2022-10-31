package com.ssafy.mylifencut.answer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.mylifencut.answer.domain.Answer;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

	@Test
	@DisplayName("답변등록 - 공개여부를 선택할 수 없는 답변의 상태가 OPEN일 때")
	public void invalidState() {
		//given
		doReturn(Answer.builder().build()).when(answerRepository).save(any(Answer.class));
		//when
		final InvalidStateException result = assertThrows(InvalidStateException.class, () -> target.createAnswer());
		//then
		assertEquals(result.getMessage(), AnswerConstant.INVALID_STATE_ERROR_MESSAGE);
	}

}