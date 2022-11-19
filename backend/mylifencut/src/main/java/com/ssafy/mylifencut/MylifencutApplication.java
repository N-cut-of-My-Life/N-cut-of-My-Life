package com.ssafy.mylifencut;

import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MylifencutApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		Locale.setDefault(Locale.KOREA);
	}

	public static void main(String[] args) {
		SpringApplication.run(MylifencutApplication.class, args);
	}

}
