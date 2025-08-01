package exception;

/* 
 * 책을 찾을 수 없을 때 발생하는 예외
 */
public class BookNotFoundException extends LibraryException {

	public BookNotFoundException(String message) {
		super(message);
	}

	
}
