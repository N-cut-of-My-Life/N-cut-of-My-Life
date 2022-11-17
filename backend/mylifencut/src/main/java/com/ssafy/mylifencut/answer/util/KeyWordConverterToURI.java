package com.ssafy.mylifencut.answer.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyWordConverterToURI {
	public static String converter(String keyword) {
		return "http://www.maniadb.com/api/search/" + keyword + "/?sr=song&display=10&key=example&v=0.5";
	}
}
