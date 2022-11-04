package com.ssafy.mylifencut.article.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.service.ArticleService;
import com.ssafy.mylifencut.common.dto.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
	private final ArticleService articleService;

	@GetMapping("{userId}")
	public ResponseEntity<BaseResponse> getArticle(@PathVariable int userId) {
		return new ResponseEntity<>(
			BaseResponse.from(
				true
				, ArticleConstant.RETRIEVE_SUCCESS_MESSAGE
				, articleService.retrieveArticles(userId))
			, HttpStatus.OK);
	}
}
