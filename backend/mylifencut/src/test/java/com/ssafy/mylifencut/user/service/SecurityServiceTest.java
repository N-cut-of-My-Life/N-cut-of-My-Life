package com.ssafy.mylifencut.user.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("시큐리티 Service 테스트")
class SecurityServiceTest {

	@InjectMocks
	private SecurityService securityService;
	@Mock
	private UserRepository userRepository;

	@Test
	@DisplayName("사용자 검색 실패")
	void searchUserFail() {
		// given
		String userId = "1";
		doThrow(new UserNotFoundException())
			.when(userRepository)
			.findById(1);

		// when

		// then
		assertThrows(UserNotFoundException.class,
			() -> securityService.loadUserByUsername(userId));
	}

	@Test
	@DisplayName("사용자 검색 성공")
	void searchUserSuccess() {
		// given
		String userId = "1";
		final UserDetails user = User.builder()
			.id(1)
			.email("SSAFY@email.com")
			.build();
		doReturn(Optional.of(user))
			.when(userRepository)
			.findById(1);

		// when
		final UserDetails result = securityService.loadUserByUsername(userId);

		// then
		assertEquals(user.getUsername(), result.getUsername());
	}
}