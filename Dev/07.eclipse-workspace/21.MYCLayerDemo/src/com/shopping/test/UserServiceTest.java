package com.shopping.test;

import java.io.File;

import com.shopping.model.User;
import com.shopping.service.UserService;

public class UserServiceTest {

	private static UserService userService;
	private static int totalTests = 0;
	private static int passedTests = 0;
	private static int failedTests = 0;
	
	public static void main(String[] args) {
		System.out.println("===================================");
		System.out.println("    UserService 비즈니스 로직 테스트    ");
		System.out.println("===================================\n");
		
		setUp();
		
		// 테스트 실행
		System.out.println("1. 회원가입 기능 테스트");
		System.out.println("----------------------------------------");
		testRegisterSuccess();
		testRegisterWithDuplicate();
	}
	
	/*
	 *  중복 ID로 회원가입 시도
	 */
	private static void testRegisterWithDuplicate() {
		String testName = "중복 ID 방지";
		totalTests++;
		
		try {
			// Given (첫번째 사용자 등록)
			String duplicateId = "duplicateId";
			userService.register(duplicateId, "pass1234", "첫번째 사용자");
			
			// When (같은 ID로 두번째 등록 시도)
			try {
				userService.register(duplicateId, "pass5678", "두번째 사용자");
				printTestResult(testName, false, "중복 ID가 허용됨");
			} catch (IllegalArgumentException e) {
				//Then : 예외 발생 확인
				boolean isCorrectException = e.getMessage().contains("이미 존재하는 ID");
				printTestResult(testName, isCorrectException, 
						isCorrectException ? "" : "잘못된 예외 메시지");
			}
			
		} catch (Exception e) {
			printTestResult(testName, false, "예상치 못한 예외 : " + e.getMessage());
		}
	}

	/*
	 * 정상적인 회원 가입
	 */
	private static void testRegisterSuccess() {
		String testName = "정상 회원가입";
		totalTests++;

		try {
			// Given (준비)
			String id = "testuser001";
			String password = "pass1234";
			String name = "이순신";
			
			// When (실행)
			User user = userService.register(id, password, name);
			
			// Then
			boolean success = true;
			String failReason = "";
			
			if (user == null) {
				success = false;
				failReason = "User 객체가 null";
			} else if (!id.equals(user.getId())) {
				success = false;
				failReason = "ID 불일치";
			} else if (!name.equals(user.getName())) {
				success = false;
				failReason = "이름 불일치";
			} else if (user.getBalance() != 10000.0) {
				success = false;
				failReason = "초기 잔액 오류";
			}
			
			printTestResult(testName, success, failReason); // 결과 출력 메서드
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			printTestResult(testName, false, testName);
		}
	}

	// 테스트 결과 출력
	private static void printTestResult(String testName, boolean success, String failReason) {
		if(success) {
			passedTests++;
			System.out.printf("%-20s : PASS\n", testName);
		} else {
			failedTests++;
			System.out.printf("%-20s : FAIL\n", testName);
			if(!failReason.isEmpty()) {
				System.out.printf("%s", failReason);
			}
		}
	}

	// 테스트 환경 설정
	private static void setUp() {
		// 기존 테스트 데이터 삭제
		clearTestData();
		
		// UserService 인스턴스 생성
		userService = new UserService();
		
		System.out.println("테스트 준비 완료\n");
	}

	private static void clearTestData() {
		try {
			File file = new File("data/users.dat");
			if(file.exists()) {
				file.delete();
				System.out.println("[초기화] 기존 데이터 삭제");
			}
		} catch(Exception e) {
			System.err.println("[경고] 데이터 초기화 실패 : " + e. getMessage());
		}
		
	}
}
