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
	public void findUserIdByRefreshTokenFail() {
		// given
		String refreshToken = "REFRESH_TOKEN";

		// when
		final Optional<Integer> userId = refreshTokenRepository.findByToken(refreshToken);

		// then
		assertFalse(userId.isPresent());
	}

	@DisplayName("리프레쉬 토큰 저장")
	@Test
	public void findUserIdByRefreshTokenSuccess() {
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