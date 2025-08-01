package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import exception.LibraryException;

/*
 * 도서 관리 시스템의 메인 클래스
 * 사용자 인터페이스와 프로그램 실행을 담당
 */
public class Member extends Person {
	private LocalDate joinDate;		// 가입일
	private ArrayList<String> borrowedBooks;	// 대출한 책 ISBN 리스트
	private static final int MAX_BORROW = 3;	// 최대 대출 가능 권 수 
	
	public Member(String id, String name, String phone) {
		super(id, name, phone);
		this.joinDate = LocalDate.now();
		this.borrowedBooks = new ArrayList<>();
	}

	@Override
	public String getRole() {
		return "회원";
	}

	/*
	 * 대출 가능 여부 확인
	 * 대출 가능하면 true, 불가능하면 false
	 */
	public boolean canBorrow() {
		return borrowedBooks.size() < MAX_BORROW;
	}
	
	/*
	 * 책 대출
	 * 대출할 책의 ISBN
	 * LibraryException - 대출이 불가한 경우
	 */
	public void borrowBook(String isbn) throws LibraryException {
		// 최대 대출 가능 권수 확인
		if(!canBorrow()) {
			throw new LibraryException("최대 대출 권수 (" + MAX_BORROW + "권)을 초과했습니다.");
		}
		
		// 이미 대출한 책인지 확인
		if(borrowedBooks.contains(isbn)) {
			throw new LibraryException("이미 대출한 책입니다.");
		}
		
		// 대출 목록에 추가
		borrowedBooks.add(isbn);
	}
	
	/*
	 * 책 반납
	 * parameter : 반납할 책의 isbn
	 * return : 성공적으로 반납되면 true, 대출하지 않은 책이면 false
	 */
	public boolean returnBook(String isbn) {
		return borrowedBooks.remove(isbn);
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}
	
	/*
	 * 대출한 책 목록 반환 (방어적 복사)
	 * 	- 내부 데이터 보호를 위해 원본이 아닌 복사본 주는 것
	 */
	public ArrayList<String> getBorrowedBooks() {
		return new ArrayList<>(borrowedBooks);
	}
	
	/*
	 * 현재 대출한 책의 개수 반환
	 */
	public int getBorrowCount() {
		return borrowedBooks.size();
	}

	/*
	 * 회원 정보를 문자열로 반환
	 */
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return super.toString() + String.format(", 가입일 : %s, 대출중 : %d 권", joinDate.format(formatter), 
				borrowedBooks.size());
	}
	
	
}


































