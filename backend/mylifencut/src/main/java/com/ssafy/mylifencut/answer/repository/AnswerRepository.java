package com.ssafy.mylifencut.answer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.answer.domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
