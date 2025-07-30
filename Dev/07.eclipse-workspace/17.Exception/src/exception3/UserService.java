package exception3;

public class UserService {
	
	
	// 회원 가입 메서드
	public void registerUser(String userId, String password, String email)  throws UserValidationException {
			
			System.out.println("사용자 등록 프로세스 시작");
			System.out.println(" 입력 값 - ID : " + userId + ", PW : " + password + ", Email : " + email);
		
			// 1단계 : 사용자 ID 검증
			System.out.println("1단계 : 사용자 ID 검증 중...");
			validateUserId(userId);
			System.out.println("사용자 ID 검증 통과");
			
			// 2단계 : 패스워드 검증
			System.out.println("2단계 : 패스워드 검증 중...");
			validatePassword(password);
			System.out.println("사용자 ID 검증 통과");
			
			// 3단계 : 이메일 검증
			System.out.println("3단계 : 이메일 검증 중...");
			validateEmail(email);
			System.out.println("이메일 검증 통과");
			
			// 4단계 : 실제 사용자 등록 -- DB 저장 (INSERT 쿼리 실행)
			System.out.println("4단계 : 데이터베이스에 사용자 정보 저장");
			
			System.out.println("사용자 등록 완료 : " + userId);
	}
	
	
	/*
	 * 이메일 검증 메서드
	 */
	private void validateEmail(String email) throws UserValidationException {
		// null 체크
		if(email == null) {
			throw new UserValidationException("EML001", "이메일은 필수 입력 항목입니다.");
		}
		
		// 이메일 형식 체크
		if(!email.contains("@")) {
			throw new UserValidationException("EML002", "올바른 이메일 형식이 아닙니다. (@ 없음)");
		}
		
		// 이메일 길이 체크
		if(email.length() > 100) {
			throw new UserValidationException("PW003", "이메일은 최대 100자 이하여야 합니다. 현재 : " 
					+ email.length() + "자");
		}
	}

	/*
	 * 패스워드 검증 메서드
	 */
	private void validatePassword(String password) throws UserValidationException {
		// null 체크
		if(password == null) {
			throw new UserValidationException("PW001", "패스워드는 필수 입력 항목입니다.");
		}
		
		// 최소 길이 체크
		if(password.length() < 8) {
			throw new UserValidationException("PW002", "패스워드는 최소 8자 이상이어야 합니다. 현재 : " 
					+ password.length() + "자");
		}
		
		// 최대 길이 체크
		if(password.length() > 20) {
			throw new UserValidationException("PW003", "패스워드는 최대 20자 이상이어야 합니다. 현재 : " 
					+ password.length() + "자");
		}
		
		// 영문 대소문자, 숫자 포함
		boolean hasUpper = password.matches(".*[A-Z].*");	// 대문자 포함
		boolean hasLower = password.matches(".*[a-z].*");	// 소문자 포함
		boolean hasDigit = password.matches(".*[0-9].*");	// 숫자 포함
		
		if(!hasUpper || !hasLower || !hasDigit) {
			throw new UserValidationException("PW004", "패스워드는 영문 대소문자와 숫자를 모두 포함해야 합니다.");
		}
	}

	/*
	 * 사용자 ID 검증 메서드
	 */
	private void validateUserId(String userId) throws UserValidationException {
		// null 체크
		if(userId == null) {
			throw new UserValidationException("USR001", "사용자 ID는 필수 입력 항목입니다.");
		}
		
		// 빈문자열 체크 (공백 제거 후)
		if(userId.trim().isEmpty()) {
			throw new UserValidationException("USR002", "사용자 ID는 필수 입력 항목입니다.");
		}
		
		// 길이 체크
		if(userId.length() < 5 ) {
			throw new UserValidationException("USR003", "사용자 ID는 최소 5자 이상이어야 합니다. 현재 : " 
					+ userId.length() + "자");
		}
	}
}
