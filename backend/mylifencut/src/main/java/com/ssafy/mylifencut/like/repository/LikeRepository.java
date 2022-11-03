package com.ssafy.mylifencut.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.like.domain.IsLike;

public interface LikeRepository extends JpaRepository<IsLike, Integer> {
	Optional<IsLike> findByUserIdAndAnswerId(Integer userId, Integer answerId);
}
