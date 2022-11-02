package com.ssafy.mylifencut.user.service;

import org.springframework.stereotype.Service;

import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public String getAccessToken(String code) {
		return null;
	}

}
