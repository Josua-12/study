package exception;

public class BookAlreadyBorrowedException extends LibraryException {

	public BookAlreadyBorrowedException(String message) {
		super(message);
	}

}
