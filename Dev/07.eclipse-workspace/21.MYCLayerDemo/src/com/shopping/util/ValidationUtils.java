package com.shopping.util;

public class ValidationUtils {

	/*
	 *  문자열(str) 빈 값 체크 (null + 공백) 
	 */
	public static void requireNonEmpty(String str, String message) {
		if (str == null || str.trim().isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/*
	 * 	문자열의 최소 길이를 체크
	 */
	public static void requireMinLength(String str, int minLength, String message) {
		requireNonNull(str, message);
		if (str.length() < minLength) {
			throw new IllegalArgumentException(message);
		}
	}

	// null 체크
	private static void requireNonNull(Object obj, String message) {
		if (obj == null) {
			throw new IllegalArgumentException(message);
		}
	}

	
	
}
