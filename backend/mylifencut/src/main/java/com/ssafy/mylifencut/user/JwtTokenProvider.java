package com.ssafy.mylifencut.user;

import java.util.Base64;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ssafy.mylifencut.user.dto.TokenResponse;
import com.ssafy.mylifencut.user.service.SecurityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

	@Value("${spring.jwt.secretKey}")
	private String secretKey;
	private final long accessTokenValidTime = 30 * 60 * 1000L;    // access 토큰 유효시간 30분
	private final long refreshTokenValidTime = 14 * 24 * 60 * 60 * 1000L;    // refresh 토큰 유효시간 14일
	private final SecurityService securityService;

	@PostConstruct
	protected void init() {
		if (secretKey == null)
			secretKey = "asdfasbasdwaegadbasdfashwedfgwagadhsdf";
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public TokenResponse createToken(String userId) {
		Claims claims = Jwts.claims().setSubject(userId);
		Date now = new Date();

		String accessToken = Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(new Date(now.getTime() + accessTokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();

		String refreshToken = Jwts.builder()
			.setExpiration(new Date(now.getTime() + refreshTokenValidTime))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();

		return TokenResponse.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.build();
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = securityService.loadUserByUsername(getUserId(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserId(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		return req.getHeader("X-AUTH-TOKEN");
	}

	public boolean validateToken(String jwtToken) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
}
