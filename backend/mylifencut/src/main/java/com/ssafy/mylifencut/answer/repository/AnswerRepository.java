package com.ssafy.mylifencut.answer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	List<Answer> findAllByState(State state);

	Optional<Answer> findById(int id);
}
