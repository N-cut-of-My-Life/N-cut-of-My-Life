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
	@DisplayName("마이페이지에서 과거 기록들 전체 조회 성공")
	public void RetrieveMyPage() {
		//given
		final User foundUser = userRepository.findById(5);
		assertNotNull(foundUser);
		assertEquals(foundUser.getId(), 5);
	}
}
