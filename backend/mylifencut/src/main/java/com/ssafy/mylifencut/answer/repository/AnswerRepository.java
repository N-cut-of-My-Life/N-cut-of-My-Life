package com.ssafy.mylifencut.answer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	List<Answer> findByState(State state);
}
