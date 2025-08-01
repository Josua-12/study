package exception;

/*
 * 도서관 시스템의 기본 예외 클래스
 * 다른 모든 도서관 예외의 부모 클래스
 */
public class LibraryException extends Exception {

	// 예외 메시지를 포함한 예외 생성자
	public LibraryException(String message) {
		super(message);
	}
}





























