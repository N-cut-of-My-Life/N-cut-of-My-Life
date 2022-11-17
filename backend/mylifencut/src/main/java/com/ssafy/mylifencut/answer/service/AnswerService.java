package com.ssafy.mylifencut.answer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.repository.AnswerRepository;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.like.repository.LikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
	private final AnswerRepository answerRepository;
	private final LikeRepository likeRepository;

	public List<GalleryResponse> getGalleryList(Integer userId) {
		List<GalleryResponse> galleryResponses = answerRepository.findAllByState(State.OPEN).stream()
			.map(GalleryResponse::of)
			.collect(Collectors.toList());

		for (int i = 0; i < galleryResponses.size(); i++) {
			Optional<IsLike> result = likeRepository.findByUserIdAndAnswerId(userId,
				galleryResponses.get(i).getAnswerId());
			if (result.isPresent()) {
				galleryResponses.get(i).setIsMine();
			}
		}

		return galleryResponses;
	}

}
