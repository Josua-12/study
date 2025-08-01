package test;

import exception.BookAlreadyBorrowedException;
import exception.BookNotFoundException;
import exception.LibraryException;
import exception.MemberNotFoundException;

public class ExceptionTest {

	public static void main(String[] args) {
		// 기본 예외
		try {
			throw new LibraryException("도서관 기본 예외 테스트");
		} catch(LibraryException e) {
			System.out.println("LibraryException : " + e.getMessage());
		}
		
		try {
			throw new BookAlreadyBorrowedException("이미 대출된 책입니다.");
		} catch(BookAlreadyBorrowedException e) {
			System.out.println("BookAlreadyBorrowedException : " + e.getMessage());
		}
		
		try {
			throw new BookNotFoundException("책을 찾을 수 없습니다.");
		} catch(BookNotFoundException e) {
			System.out.println("BookNotFoundException : " + e.getMessage());
		}
		
		try {
			throw new MemberNotFoundException("회원을 찾을 수 없습니다.");
		} catch (MemberNotFoundException e) {
			System.out.println("MemberNotFoundException : " + e.getMessage());
		}
		
	}
}












