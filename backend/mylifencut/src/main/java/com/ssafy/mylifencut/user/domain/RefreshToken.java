package com.ssafy.mylifencut.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RefreshToken {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false)
	private String token;
	@Column(nullable = false)
	private Integer userId;

	public RefreshToken updateToken(String token) {
		this.token = token;
		return this;
	}
}
