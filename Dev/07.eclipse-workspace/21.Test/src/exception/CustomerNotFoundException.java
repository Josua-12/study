package exception;

/**
 * 고객을 찾을 수 없을 때 발생하는 예외
 */
public class CustomerNotFoundException extends ShopException {
    /**
     * 고객을 찾을 수 없을 때의 예외 생성자
     * @param message 예외 메시지
     */
    public CustomerNotFoundException(String message) {
        super(message);
    }
}