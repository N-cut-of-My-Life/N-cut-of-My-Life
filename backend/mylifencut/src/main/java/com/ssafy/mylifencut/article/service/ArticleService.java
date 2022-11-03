package com.ssafy.mylifencut.article.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.mylifencut.article.dto.ArticleRetrieveResponse;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;

	public List<ArticleRetrieveResponse> retrieveArticles(int userId) {
		userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new);

		return articleRepository.findAllByUserId(userId).stream()
			.map(ArticleRetrieveResponse::of)
			.collect(Collectors.toList());
	}
}
