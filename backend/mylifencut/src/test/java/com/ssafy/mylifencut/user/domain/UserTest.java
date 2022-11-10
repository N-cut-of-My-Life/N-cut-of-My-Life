package com.ssafy.mylifencut.user.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

@DisplayName("[유저 도메인]")
class UserTest {

	@Test
	@DisplayName("[성공] - 비밀번호 받기")
	void getPassword() {
		// given
		final User user = User.builder().build();

		// when
		String result = user.getPassword();

		// then
		assertNull(result);
	}

	@Test
	@DisplayName("[성공] - 회원 기간 확인")
	void isAccountNonExpired() {
		// given
		final User user = User.builder().build();

		// when
		boolean result = user.isAccountNonExpired();

		// then
		assertFalse(result);
	}

	@Test
	@DisplayName("[성공] - 회원 잠금 여부 확인")
	void isAccountNonLocked() {
		// given
		final User user = User.builder().build();

		// when
		boolean result = user.isAccountNonLocked();

		// then
		assertFalse(result);
	}

	@Test
	@DisplayName("[성공] - 신임 여부 확인")
	void isCredentialsNonExpired() {
		// given
		final User user = User.builder().build();

		// when
		boolean result = user.isCredentialsNonExpired();

		// then
		assertFalse(result);
	}

	@Test
	@DisplayName("[성공] - 유효성 확인")
	void isEnabled() {
		// given
		final User user = User.builder().build();

		// when
		boolean result = user.isEnabled();

		// then
		assertFalse(result);
	}

	@Test
	@DisplayName("[성공] - 역할 확인")
	void getAuthorities() {
		// given
		final User user = User.builder()
			.authorities(Collections.singletonList(
				Authority.builder()
					.role(Role.USER)
					.build()))
			.build();

		// given
		Collection<? extends GrantedAuthority> result = user.getAuthorities();

		// when
		assertEquals(1, result.size());
	}
}