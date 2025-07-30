package exception3;

/*
 * 사용자 검증 관련 커스텀 예외 클래스 
 * 	- 비즈니스 로직에 특화된 명확한 오류 메시지
 * 	- 에러 코드를 통한 체계적인 오류 관리
 */
public class UserValidationException extends Exception {
	// 에러 코드 필드
	private String errorCode;
	
	// 커스텀 예외 클래스 생성자 -- 사용자에게 표시할 메시지
	public UserValidationException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	// 에러 코드 조회 메서드
	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return String.format("[%s] %s", errorCode, getMessage());
	}
	
	
	
}
