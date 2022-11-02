package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.user.domain.User;

@DataJpaTest
public class MyPageRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void repositoryIsNotNull() {
		assertNotNull(userRepository);
	}

	@Test
	@DisplayName("마이페이지에서 과거 기록 리스트가 없을 때(사이즈가 0이어야함) - 성공")
	public void RetrieveMyPage() {
		//given
		final User user = User.builder()
			.email("test@email.com")
			.name("이름")
			.build();

		//when
		final User savedUser = userRepository.save(user);

		//then
		assertNotNull(savedUser);

		final Optional<User> foundUser = userRepository.findById(savedUser.getId());
		assertTrue(foundUser.isPresent());
		assertNotNull(foundUser.get());
		assertEquals(savedUser.getId(), foundUser.get().getId());
		assertEquals(foundUser.get().getArticles().size(), 0);
	}
}
