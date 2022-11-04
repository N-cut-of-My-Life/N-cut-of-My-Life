package com.ssafy.mylifencut.user.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.ssafy.mylifencut.user.domain.User;
import com.ssafy.mylifencut.user.dto.UserInfo;
import com.ssafy.mylifencut.user.exception.InvalidKakaoAccessTokenException;
import com.ssafy.mylifencut.user.exception.UserNotFoundException;
import com.ssafy.mylifencut.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	@Value("${oauth2.kakao.restApiKey}")
	private String kakao_restApiKey;

	@Value("${oauth2.kakao.redirectUri}")
	private String kakao_redirectUri;

	public Integer kakaoLogin(String token) {
		UserInfo userInfo = getUserInfo(getAccessToken(token));

		User user = userRepository.findByEmail(userInfo.getEmail())
			.orElse(join(userInfo));

		return user.getId();
	}

	public String getAccessToken(String code) {
		String access_Token;
		String refresh_Token;
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			//POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			//POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			String sb = "grant_type=authorization_code" + "&client_id=" + kakao_restApiKey + "&redirect_uri="
				+ kakao_redirectUri + "&code=" + code;
			bw.write(sb);
			bw.flush();
			bw.close();

			String result = getResult(conn);

			//Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonElement element = JsonParser.parseString(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

			log.info("access_token : " + access_Token);
			log.info("refresh_token : " + refresh_Token);

		} catch (IOException e) {
			throw new InvalidKakaoAccessTokenException();
		}

		return access_Token;
	}

	public UserInfo getUserInfo(String token) {
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		//access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

			String result = getResult(conn);

			//Gson 라이브러리로 JSON파싱
			JsonElement element = JsonParser.parseString(result);

			return UserInfo.builder()
				.email(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString())
				.name(element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("nickname").getAsString())
				.build();
		} catch (IOException e) {
			throw new InvalidKakaoAccessTokenException();
		}
	}

	private String getResult(HttpURLConnection conn) throws IOException {
		//결과 코드가 200이라면 성공
		int responseCode = conn.getResponseCode();
		log.info("responseCode : " + responseCode);

		//요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		StringBuilder result = new StringBuilder();

		while ((line = br.readLine()) != null) {
			result.append(line);
		}
		log.info("response body : " + result);
		br.close();

		return result.toString();
	}

	public boolean isExistingUser(UserInfo userInfo) {
		return userRepository.findByEmail(userInfo.getEmail()).isPresent();
	}

	public User join(UserInfo userInfo) {
		return userRepository.save(User.from(userInfo));
	}

	public User login(UserInfo userInfo) {
		return userRepository.findByEmail(userInfo.getEmail())
			.orElseThrow(UserNotFoundException::new);
	}
}
