package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.user.domain.RefreshToken;

@DataJpaTest
@DisplayName("리프레쉬 토큰 레포 테스트")
class RefreshTokenRepositoryTest {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@DisplayName("리프레쉬 토큰으로 UserId 검색")
	@Test
	void findUserIdByRefreshTokenFail() {
		// given
		String refreshToken = "REFRESH_TOKEN";

		// when
		final Optional<RefreshToken> userId = refreshTokenRepository.findByToken(refreshToken);

		// then
		assertFalse(userId.isPresent());
	}

	@DisplayName("userId으로 리프레쉬 토큰 검색")
	@Test
	void findRefreshTokenByUserIdFail() {
		// given
		Integer userId = 1;

		// when
		final Optional<RefreshToken> result = refreshTokenRepository.findByUserId(userId);

		// then
		assertFalse(result.isPresent());
	}

	@DisplayName("리프레쉬 토큰 저장")
	@Test
	void findUserIdByRefreshTokenSuccess() {
		// given
		RefreshToken refreshToken = RefreshToken.builder()
			.userId(1)
			.token("TOKEN")
			.build();

		// when
		final RefreshToken result = refreshTokenRepository.save(refreshToken);

		// then
		assertEquals(refreshToken.getUserId(), refreshToken.getUserId());
		assertEquals(refreshToken.getToken(), refreshToken.getToken());
	}
}