package com.ssafy.mylifencut.user.repository;

import com.ssafy.mylifencut.user.domain.RefreshToken;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);

	List<RefreshToken> findAllByUserId(Integer id);
}
