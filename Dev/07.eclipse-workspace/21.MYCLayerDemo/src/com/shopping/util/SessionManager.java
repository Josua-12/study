package com.shopping.util;

import com.shopping.model.User;

/*
 *  사용자 세션을 관리하는 유틸리티 클래스 
 */
public class SessionManager {
	
	// 현재 로그인한 사용자 정보
	private static User currentUser = null;
	
	private SessionManager() {
		// 유틸리티 클래스이므로 인스턴스 생성 방지
	}
	
	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User user) {
		SessionManager.currentUser = user;
		System.out.println("세션 생성 : " + user.getName() + "님 로그인");
	}

	// 로그인 상태 확인
	public static boolean isLoggedIn() {
		
		return currentUser != null;
	}

}
