package com.ssafy.mylifencut.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.mylifencut.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
