package com.ssafy.mylifencut.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.mylifencut.answer.dto.AnswerRegisterRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
	private Integer userId;
	private List<AnswerRegisterRequest> answers;
	private LocalDateTime createDate = LocalDateTime.now();
}