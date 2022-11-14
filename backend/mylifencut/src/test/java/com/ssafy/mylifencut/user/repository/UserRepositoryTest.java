package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
@DisplayName("[유저 레포]")
class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("[성공] - 회원 가입")
	void register() {
		// given
		final User user = User.builder()
			.name("홍길동")
			.email("ssafy@email.com")
			.profileImage("IMAGE_URL")
			.articles(Collections.emptyList())
			.build();

		// when
		final User result = userRepository.save(user);

		// then
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getProfileImage(), result.getProfileImage());
		assertEquals(user.getArticles(), result.getArticles());
	}

	@Test
	@DisplayName("[성공] - 이메일로 회원 여부 검사(신규회원)")
	void findUserByEmailFail() {
		// given
		final String email = "ssafy@email.com";

		//when
		final Optional<User> result = userRepository.findByEmail(email);

		//then
		assertFalse(result.isPresent());
	}

	@Test
	@DisplayName("[성공] - 이메일로 회원 여부 검사(기존회원)")
	void findUserByEmailSuccess() {
		// given
		final User user = User.builder()
			.name("홍길동")
			.email("ssafy@email.com")
			.articles(Collections.emptyList())
			.build();
		final User savedUser = userRepository.save(user);

		//when
		final Optional<User> result = userRepository.findByEmail(savedUser.getEmail());

		//then
		assertTrue(result.isPresent());
		assertEquals(savedUser.getName(), result.get().getName());
		assertEquals(savedUser.getEmail(), result.get().getEmail());
		assertEquals(savedUser.getProfileImage(), result.get().getProfileImage());
		assertEquals(savedUser.getArticles(), result.get().getArticles());
	}
}
