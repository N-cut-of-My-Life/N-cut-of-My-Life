package com.ssafy.mylifencut.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.like.domain.IsLike;

public interface LikeRepository extends JpaRepository<IsLike, Integer> {
	IsLike findByUserIdAndAnswerId(Integer userId, Integer answerId);
}
