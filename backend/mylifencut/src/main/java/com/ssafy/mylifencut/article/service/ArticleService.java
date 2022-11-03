package com.ssafy.mylifencut.article.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.dto.ArticleResponse;
import com.ssafy.mylifencut.article.exception.NotFoundUserException;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;

	public List<ArticleResponse> retrieveArticles(int userId) {
		userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException(ArticleConstant.NOT_FOUND_USER_ERROR_MESSAGE));

		return articleRepository.findAllByUserId(userId).stream()
			.map(ArticleResponse::of)
			.collect(Collectors.toList());
	}
}
