package exception;

/**
 * 쇼핑몰 시스템의 기본 예외 클래스
 * 다른 모든 쇼핑몰 관련 예외의 부모 클래스
 */
public class ShopException extends Exception {
    /**
     * 메시지를 포함한 예외 생성자
     * @param message 예외 메시지
     */
    public ShopException(String message) {
        super(message);
    }
}




























