package com.ssafy.mylifencut.answer.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
	private final AnswerRepository answerRepository;

	public List<GalleryResponse> getGalleryList() {
		return answerRepository.findAllByState(State.OPEN).stream()
			.map(GalleryResponse::of)
			.collect(Collectors.toList());
	}

}
