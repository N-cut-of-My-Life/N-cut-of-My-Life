package com.ssafy.mylifencut.article.service;

import org.springframework.stereotype.Service;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.exception.NotFoundUserException;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;

	public void retrieveArticles(int userId) {
		userRepository.findById(userId)
			.orElseThrow(() -> new NotFoundUserException(ArticleConstant.NOT_FOUND_USER_ERROR_MESSAGE));
		articleRepository.findAllByUserId(userId);
	}
}
