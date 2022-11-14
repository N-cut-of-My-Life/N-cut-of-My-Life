package com.ssafy.mylifencut.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ssafy.mylifencut.user.domain.RefreshToken;

@DataJpaTest
@DisplayName("[리프레쉬 토큰]")
class RefreshTokenRepositoryTest {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @DisplayName("토큰 검색")
    @Nested
    class findToken {

        @DisplayName("[실패] -  UserId로 토큰 검색")
        @Test
        void findByRefreshTokenFail() {
            // given
            String refreshToken = "REFRESH_TOKEN";

            // when
            final Optional<RefreshToken> userId = refreshTokenRepository.findByToken(refreshToken);

            // then
            assertFalse(userId.isPresent());
        }

        @DisplayName("[성공]")
        @Test
        void findByRefreshTokenSuccess() {
            //given
            final RefreshToken refreshToken = RefreshToken.builder()
                    .token("TOKEN")
                    .userId(1)
                    .build();
            refreshTokenRepository.save(refreshToken);

            // when
            Optional<RefreshToken> result = refreshTokenRepository.findByToken("TOKEN");

            // then
            assertTrue(result.isPresent());
            assertEquals(result.get().getToken(), refreshToken.getToken());
            assertEquals(result.get().getUserId(), refreshToken.getUserId());
        }
    }

    @DisplayName("UserId로 전체 리스트 검색")
    @Nested
    class FindAllByUser {

        @DisplayName("[실패] - 리프레쉬 토큰 없음")
        @Test
        void findAllByUserIdFail() {
            // given
            final Integer userId = 1;

            // when
            final List<RefreshToken> result = refreshTokenRepository.findAllByUserId(userId);

            // then
            assertEquals(0, result.size());
        }

        @DisplayName("[성공]")
        @Test
        void findAllByUserIdSuccess() {
            // given
            final Integer userId = 1;
            final RefreshToken refreshToken1 = RefreshToken.builder()
                    .token("TOKEN1")
                    .userId(userId)
                    .build();
            final RefreshToken refreshToken2 = RefreshToken.builder()
                    .token("TOKEN2")
                    .userId(userId)
                    .build();
            final RefreshToken refreshToken3 = RefreshToken.builder()
                    .token("TOKEN3")
                    .userId(2)
                    .build();
            refreshTokenRepository.save(refreshToken1);
            refreshTokenRepository.save(refreshToken2);
            refreshTokenRepository.save(refreshToken3);

            // when
            List<RefreshToken> result = refreshTokenRepository.findAllByUserId(userId);

            // then
            assertEquals(2, result.size());
            result.forEach(token -> {
                assertEquals(token.getUserId(), userId);
            });
        }
    }

    @DisplayName("[성공] - 리프레쉬 토큰 저장")
    @Test
    void findUserIdByRefreshTokenSuccess() {
        // given
        RefreshToken refreshToken = RefreshToken.builder()
                .userId(1)
                .token("TOKEN")
                .build();

        // when
        final RefreshToken result = refreshTokenRepository.save(refreshToken);

        // then
        assertEquals(refreshToken.getUserId(), refreshToken.getUserId());
        assertEquals(refreshToken.getToken(), refreshToken.getToken());
    }
}