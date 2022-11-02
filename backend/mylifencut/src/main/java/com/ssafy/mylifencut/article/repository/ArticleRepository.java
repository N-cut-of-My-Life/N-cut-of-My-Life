package com.ssafy.mylifencut.article.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	List<Article> findByUserId(int userId);
}
