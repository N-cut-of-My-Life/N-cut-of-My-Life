package com.ssafy.mylifencut.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.like.domain.isLike;

public interface LikeRepository extends JpaRepository<isLike, Integer> {
}
