package com.ssafy.mylifencut.user.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ssafy.mylifencut.article.domain.Article;
import com.ssafy.mylifencut.like.domain.IsLike;
import com.ssafy.mylifencut.user.dto.UserInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@Table(name = "member")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<Article> articles = new ArrayList<>();

	private String email;

	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<IsLike> likes = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "user")
	private List<Authority> authorities = new ArrayList<>();

	public void addArticle(Article article) {
		this.articles.add(article);
	}

	public static User from(UserInfo userInfo) {
		return User.builder()
			.name(userInfo.getName())
			.email(userInfo.getEmail())
			.build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream()
			.map(authority -> new SimpleGrantedAuthority(authority.getRole().toString()))
			.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
