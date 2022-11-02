package com.ssafy.mylifencut.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.article.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
