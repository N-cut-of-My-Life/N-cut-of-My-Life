package com.ssafy.mylifencut.article.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.user.domain.User;

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
	private Integer id;
	private User user;
	private List<Answer> answers;
	private LocalDateTime createDate;
}