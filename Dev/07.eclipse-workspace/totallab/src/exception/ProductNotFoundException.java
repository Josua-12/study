package exception;

/**
 * 상품을 찾을 수 없을 때 발생하는 예외
 */
public class ProductNotFoundException extends ShopException {
    /**
     * 상품을 찾을 수 없을 때의 예외 생성자
     * @param message 예외 메시지
     */
    public ProductNotFoundException(String message) {
        super(message);
    }
}