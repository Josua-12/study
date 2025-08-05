package exception;

/**
 * 재고 부족 시 발생하는 예외
 */
public class InsufficientStockException extends ShopException {
    /**
     * 재고 부족 예외 생성자
     * @param message 예외 메시지
     */
    public InsufficientStockException(String message) {
        super(message);
    }
}
