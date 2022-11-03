package com.ssafy.mylifencut.article.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.dto.ArticleRegisterRequest;
import com.ssafy.mylifencut.article.dto.ArticleRetrieveResponse;
import com.ssafy.mylifencut.article.exception.AnswersSizeIsNotEnough;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<ArticleRetrieveResponse> retrieveArticles(int userId) {
		userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new);

		return articleRepository.findAllByUserId(userId).stream()
			.map(ArticleRetrieveResponse::of)
			.collect(Collectors.toList());
	}

	public void createArticle(ArticleRegisterRequest articleRegisterRequest) {
		userRepository.findById(articleRegisterRequest.getUser().getId())
			.orElseThrow(UserNotFoundException::new);

		if (articleRegisterRequest.getAnswers().size() < ArticleConstant.ANSWERS_MIN_SIZE) {
			throw new AnswersSizeIsNotEnough();
		}
		articleRepository.save(Article.from(articleRegisterRequest));
	}
}
