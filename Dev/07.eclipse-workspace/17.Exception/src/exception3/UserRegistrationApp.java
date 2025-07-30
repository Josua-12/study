package exception3;

public class UserRegistrationApp {

	public static void main(String[] args) {
		System.out.println("사용자 ID, 패스워드, 이메일 등록 검증");
		
		UserService userService = new UserService();
		
		// 2차원 배열 -- [사용자 ID, 패스워드, 이메일, 설명]
		String[][] registerCases = {
				{"dev001", "SecurePass123", "dev001@gmail.com", "정상 케이스"},
				{"qaa002", "TestPass123", "qaa002@gmail.com", "정상 케이스"},
				{null, "TestPass123", "qaa002@gmail.com", "ID가 null"},
				{"dev001", "Tes", "qaa002@gmail.com", "PWD가 너무 짧음"},
				{"dev001", "testpass", "qaa002@gmail.com", "PWD에 대문자/숫자 없음"},
				{"qaa002", "TestPass123", "qaa002gmail.com", "잘못된 이메일 형식"}
		};
		
		// 각 케이스를 순서대로 실행
		for(int i = 0; i < registerCases.length; i++) {
			String userId = registerCases[i][0];
			String password = registerCases[i][1];
			String email = registerCases[i][2];
			String description = registerCases[i][3];
			
			System.out.println("테스트 케이스 " + (i + 1) + "/" +registerCases.length);
			System.out.println("테스트 시나리오 : " + description);
			
			try {
				
				userService.registerUser(userId, password, email);
				
				System.out.println("결과 : 등록 성공 !");
				
			} catch (UserValidationException e) {
				
				//e.printStackTrace();
				// 커스텀 예외 처리
				System.out.println("결과 : 등록 실패");
				System.out.println("에러코드 : " + e.getErrorCode());
				System.out.println("에러 메시지 : " + e.getMessage());
				
				// 에러 코드별로 다르게 처리함
				handlErrorCode(e.getErrorCode(), e.getMessage());
			}
			
			System.out.println("-".repeat(60) + "\n");
		}
		System.out.println("테스트 케이스 실행 완료");
		
	}

	/*
	 * 에러 코드별 처리 메서드 
	 * 에러 코드에 따라 다른 로직을 수행할 수 있음
	 */
	private static void handlErrorCode(String errorCode, String message) {
		System.out.println("에러 코드별 후속 처리 : ");
		// 에러 코드 앞 3자리로 구분
		String category = errorCode.substring(0, 3);
		
		switch(category) {
			case "USR" :
				System.out.println("카테고리 : 사용자 ID 관련 오류");
				System.out.println("권장 조치 : 사용자에게 ID 입력 가이드 제공");
				// 여기서 로그 기록, 알림 발송 등
				logUserIdError(errorCode, message);
				break;
			case "EML" :
				System.out.println("카테고리 : 이메일 관련 오류");
				System.out.println("권장 조치 : 이메일 형식 예시 제공");
				// 여기서 로그 기록, 알림 발송 등
				logEmailError(errorCode, message);
				break;
			default : 
			System.out.println("카테고리 : 알 수 없는 오류");	
			System.out.println("권장 조치 : 시스템 관리자에게 문의하세요.");
			break;
		}
	}

	/*
	 * 이메일 관련 오류 로깅
	 */
	private static void logEmailError(String errorCode, String message) {
		System.out.println("로그 기록 : [EMAIL_ERROR] " + errorCode + " - " + message);
	}

	/*
	 * 사용자 ID 관련 오류 로깅
	 */
	private static void logUserIdError(String errorCode, String message) {
		// log4j, logback
		System.out.println("로그 기록 : [USER_ID_ERROR] " + errorCode + " - " + message);
	}
}
