package com.ssafy.mylifencut.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ArticleRegisterRequest {
	private Integer userId;
	private List<AnswerRegisterRequest> answers;
	private LocalDateTime createDate;
}