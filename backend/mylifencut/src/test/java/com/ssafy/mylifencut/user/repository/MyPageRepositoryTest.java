package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.mylifencut.user.domain.User;

public class MyPageRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("마이페이지에서 과거 기록 사이즈가 0이어야함")
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
		final User foundUser = userRepository.findById(savedUser.getId());
		assertNotNull(foundUser);
		assertEquals(savedUser.getId(), foundUser.getId());
		assertEquals(foundUser.getArticles().size(), 0);
	}
}
