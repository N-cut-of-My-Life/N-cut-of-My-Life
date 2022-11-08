package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RefreshTokenRepositoryTest {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@DisplayName("리프레쉬 토큰으로 UserId 검색")
	@Test
	public void findUserIdByRefreshToken() {
		// given
		String refreshToken = "REFRESH_TOKEN";

		// when
		final Optional<Integer> userId = refreshTokenRepository.findByRefreshToken();

		// then
		assertFalse(userId.isPresent());
	}
}