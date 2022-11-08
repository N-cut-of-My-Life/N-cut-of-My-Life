package com.ssafy.mylifencut.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.user.domain.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	Optional<Integer> findByRefreshToken();
}
