package com.ssafy.mylifencut.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RefreshTokenRepositoryTest {
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
}