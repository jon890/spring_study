package com.ict.human.bbs.common;

public class FormatUtils {

	// 파일의 확장자를 알아내는 유틸을 선언
	// jsp에서 함수로 쓰기 위해서
	public static String getFormatName(String file) {
		return file.substring(file.lastIndexOf("." + 1));
	}
}