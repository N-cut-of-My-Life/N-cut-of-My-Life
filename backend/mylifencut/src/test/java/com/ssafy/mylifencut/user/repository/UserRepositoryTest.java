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
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("회원 저장 성공")
	public void register() {
		// given
		final User user = User.builder()
			.name("홍길동")
			.email("ssafy@email.com")
			.articles(Collections.emptyList())
			.build();

		// when
		final User result = userRepository.save(user);

		// then
		assertEquals(user.getName(), result.getName());
		assertEquals(user.getEmail(), result.getEmail());
		assertEquals(user.getArticles(), result.getArticles());
	}

	@Test
	@DisplayName("이메일로 회원 여부 검사 - 신규회원")
	public void checkUserByEmail() {
		// given
		final String email = "ssafy@email.com";

		//when
		final Optional<User> result = userRepository.findByEmail(email);

		//then
		assertNull(result);
	}
}
