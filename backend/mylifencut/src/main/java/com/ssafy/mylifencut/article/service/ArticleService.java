package com.ssafy.mylifencut.article.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.article.ArticleConstant;
import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.article.dto.ArticleRegisterRequest;
import com.ssafy.mylifencut.article.dto.ArticleResponse;
import com.ssafy.mylifencut.article.exception.AnswersSizeIsNotEnough;
import com.ssafy.mylifencut.article.repository.ArticleRepository;
import com.ssafy.mylifencut.user.domain.User;
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
	public List<ArticleResponse> retrieveArticles(int userId) {
		userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

		return articleRepository.findAllByUserId(userId).stream().map(ArticleResponse::of).collect(Collectors.toList());
	}

	public void createArticle(ArticleRegisterRequest articleRegisterRequest) {
		User user = userRepository.findById(articleRegisterRequest.getUserId()).orElseThrow(UserNotFoundException::new);

		if (articleRegisterRequest.getAnswers().size() < ArticleConstant.ANSWERS_MIN_SIZE) {
			throw new AnswersSizeIsNotEnough();
		}
		Article article = articleRepository.save(Article.from(articleRegisterRequest, user));
		articleRegisterRequest.getAnswers().stream()
			.map(answerRegisterRequest -> Answer.from(answerRegisterRequest, article))
			.forEach(article::addAnswer);
	}
}
