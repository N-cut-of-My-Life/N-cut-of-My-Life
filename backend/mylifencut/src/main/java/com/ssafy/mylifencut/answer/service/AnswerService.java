package com.ssafy.mylifencut.answer.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mylifencut.answer.domain.Answer;
import com.ssafy.mylifencut.answer.domain.State;
import com.ssafy.mylifencut.answer.dto.GalleryResponse;
import com.ssafy.mylifencut.answer.exception.GalleryNotFoundException;
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

		for (GalleryResponse galleryResponse : galleryResponses) {
			Optional<IsLike> result = likeRepository.findByUserIdAndAnswerId(userId,
				galleryResponse.getAnswerId());
			if (result.isPresent()) {
				galleryResponse.setIsMine();
			}
		}

		return galleryResponses;
	}

	public GalleryResponse getGalleryOne(int userId, int answerId) {
		Answer answer = answerRepository.findById(answerId)
			.orElseThrow(GalleryNotFoundException::new);
		GalleryResponse galleryResponse = GalleryResponse.of(answer);
		if (likeRepository.findByUserIdAndAnswerId(userId, answerId).isPresent()) {
			galleryResponse.setIsMine();
		}
		return galleryResponse;
	}
}
