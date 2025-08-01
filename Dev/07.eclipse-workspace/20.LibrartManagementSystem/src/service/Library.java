package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import exception.BookNotFoundException;
import exception.LibraryException;
import exception.MemberNotFoundException;
import model.Book;
import model.Librarian;
import model.Member;

/*
 * 도서관의 주요 비즈니스 로직 담당 클래스
 */
public class Library {
	private HashMap<String, Book> books;			// ISBN -> Book
	private HashMap<String, Member> members;		// ID -> Member
	private HashMap<String, Librarian> librarians;	// ID -> Librarian
	private String libraryName;
	
	
	public Library(String libraryName) {
		//super();
		this.libraryName = libraryName;
		this.members = new HashMap<>();
		this.books = new HashMap<>();
		this.librarians = new HashMap<>();
	}
	
	/*
	 * 새 책을 도서관에 추가
	 */
	public void addBook(String isbn, String title, String author, String category, LocalDate publishDate) 
				throws LibraryException {
		
		if(books.containsKey(isbn) ) {
			throw new LibraryException("이미 존재하는 ISBN입니다. " + isbn);
		}
		
		// HashMap에 추가
		Book book = new Book(isbn, title, author, category, publishDate);
		books.put(isbn, book);
		System.out.println("책이 추가되었습니다.");
	}
	
	/*
	 * 제목으로 책 검색
	 * parameter : title 검색할 제목 (부분 검색 가능)
	 * return : 검색된 목록
	 */
	public ArrayList<Book> searchBooksByTitle(String title) {
		ArrayList<Book> results = new ArrayList<>();
		
		// 모든 책을 순회하며 제목에 검색어가 포함된 책 찾기
		for(Book book : books.values()) {
			if(book.getTitle().toLowerCase().contains(title.toLowerCase())) {
				results.add(book);
			}
		}
		
		return results;
	}
	
	/*
	 * 저자로 책 검색
	 * parameter : author 검색할 제목 (부분 검색 가능)
	 * return : 검색된 목록
	 */
	public ArrayList<Book> searchBooksByCategory(String category) {
		ArrayList<Book> results = new ArrayList<>();
		
		// 모든 책을 순회하며 제목에 검색어가 포함된 책 찾기
		for(Book book : books.values()) {
			if(book.getCategory().toLowerCase().contains(category.toLowerCase())) {
				results.add(book);
			}
		}
		
		return results;
	}
	
	/*
	 * 저자으로 책 검색
	 * parameter : author 검색할 제목 (부분 검색 가능)
	 * return : 검색된 목록
	 */
	public ArrayList<Book> searchBooksByAuthor(String author) {
		ArrayList<Book> results = new ArrayList<>();
		
		// 모든 책을 순회하며 저자에 검색어가 포함된 책 찾기
		for(Book book : books.values()) {
			if(book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
				results.add(book);
			}
		}
		
		return results;
	}
	
	/*
	 * 새 회원 등록
	 * - ID, 이름, 전화번호
	 * - LibraryException : 이미 존재하는 회원 ID인 경우
	 */
	public void addMember(String id, String name, String phone) throws LibraryException {
		// 중복 ID 확인
		if(members.containsKey(id)) {
			throw new LibraryException("이미 존재하는 회원 ID입니다. : " + id);
		}
		
		// 새 회원 생성 후 HashMAp에 추가
		Member member = new Member(id, name, phone);
		members.put(id, member);
		System.out.println("회원이 등록되었습니다 : " + name);
	}
	
	/*
	 * 새 사서 등록 
	 * - 사서 ID, 이름, 전화번호, 부서
	 * - LibraryException : 이미 존재하는 사서 ID인 경우
	 */
	public void addLibrarian(String id, String name, String phone, String department, String position) 
		throws LibraryException {
		// 중복 ID 확인
		if(librarians.containsKey(id)) {
			throw new LibraryException("이미 존재하는 사서 ID입니다. : " + id);
		}
		
		// 새 사서 객체 생성 후 HashMap에 추가
		Librarian librarian = new Librarian(id, name, phone, department, position);
		librarians.put(id, librarian);
		System.out.println("사서가 등록되었습니다. : " + name );
	}
	
	/*
	 * 책 대출 처리
	 * parameter : isbn, 대출할 회원 ID
	 * LibraryException 대출 처리 중 오류 발생 시
	 */
	public void borrowBook(String isbn, String memberId) throws LibraryException {
		// 책 존재 확인
		Book book = books.get(isbn);
		if(book == null) {
			throw new BookNotFoundException("존재하지 않는 책입니다 : " + isbn);
		}
		
		// 회원 존재 확인
		Member member = members.get(memberId);
		if(member == null) {
			throw new MemberNotFoundException("존재하지 않는 회원입니다 : " + member);
		}
		
		// 대출 처리
		book.borrowTo(memberId); 	// Book 객체에서 대출 처리
		member.borrowBook(isbn); 	// Member 객체 서 대출 목록 추가
		
		System.out.println(String.format("대출 완료 - 책 : %s, 회원 : %s", book.getTitle(), member.getName()));
	}
	
	/*
	 * 책 반납 처리
	 * parameter : isbn
	 * LibraryException 반납 처리 중 오류 발생 시
	 */
	public void returnBook(String isbn) throws LibraryException {
		// 책 존재 확인
		Book book = books.get(isbn);
		if(book == null) {
			throw new BookNotFoundException("존재하지 않는 책입니다 : " + isbn);
		}
		
		// 대출 상태 확인
		if(book.isAvailable()) {
			throw new LibraryException("대출되지 않은 책입니다 : " + book.getTitle());
		} 
		
		// 대출자 정보 가져오기
		String borrowerId = book.getBorrowedId();
		Member member = members.get(borrowerId);
		
		// 연체 여부 확인
		boolean overdue = book.isOverdue();
		
		// 반납 처리 : book, Member 객체 모두 업데이트
		book.returnBook();		// Book 객체에서 반납처리
		if(member != null) {
			member.returnBook(isbn);		// Member 객체에서 대출 목록 제거
		}
		
		String overdueMessage = overdue ? "(연체 반납)" : "";
		System.out.println("반납 완료 - 책 : " + book.getTitle() + overdueMessage);
	}
	
	// 전체 책 목록 출력
	public void displayAllBooks() {
		if(books.isEmpty()) {
			System.out.println("등록된 책이 없습니다.");
			return;
		}
		System.out.println("=== 전체 도서 목록 ====");
		for(Book book : books.values()) {
			System.out.println(book);
		}
		System.out.println("총 " + books.size() + "권의 책이 등록되어 있습니다.");
	}
	
	// 대출 가능한 책 목록 출력
	public void displayAvaliableBooks() {
		System.out.println("=== 대출 가능한 도서 ===");
		
		long availableCount = books.values().stream()
						//.filter(book -> book.isAvailable()) 		// 대출 가능한 책만 필터링
						.filter(Book::isAvailable)
						//.peek(book -> System.out.println(book))	// 각 책 출력
						.peek(System.out::println)
						.count();
		
		if(availableCount == 0) {
			System.out.println("현재 대출 가능한 책이 없습니다.");
		}
		
		System.out.println();
	}
	
	// 연체된 책 목록 출력
	public void displayOverdueBooks() {
		System.out.println("=== 연체된 도서 ===");
		boolean hasOverdue = false;
		
		// 모든 책을 확인하여 연체된 책 찾기
		for(Book book : books.values()) {
			if(book.isAvailable() && book.isOverdue()) {
				System.out.println(book);
				hasOverdue = true;
			}
		}
		
		if(!hasOverdue) {
			System.out.println("연체된 책이 없습니다.");
		}
		
		System.out.println();
	}
	
	/*
	 * 특정 회원의 대출 현황 출력
	 * parameter : memberId
	 * MemberNotFoundException 존재하지 않는 회원의 경우
	 */
	public void displayMemberBorrowStatus(String memberId) throws MemberNotFoundException {
		Member member =  members.get(memberId);
		if(member == null) {
			throw new MemberNotFoundException("존재하지 않는 회원입니다 : " + memberId);
		}
		
		System.out.println("\n=== " + member.getName() + " 회원의 대출 현황 ===");
		System.out.println(memberId);
		
		ArrayList<String> borrowedBooks = member.getBorrowedBooks();
		if(borrowedBooks.isEmpty()) {
			System.out.println("현재 대출한 책이 없습니다.");
		} else {
			 for(String isbn : borrowedBooks) {
				 Book book = books.get(isbn);
				 if(book != null) {
					 System.out.println(" - " + book.getTitle() + (book.isOverdue() ? "(연체됨)" : ""));
				 }
			}
		}
		System.out.println();
	}
	
	// 도서관 전체 통계 정보 출력
	public void displayLibraryStatics() {
		System.out.println("\n=== " + libraryName + " 통계 정보 ===");
		
		// 통계 정보
		int totalBooks = books.size();
		long availableBooks = books.values().stream()
								.filter(Book::isAvailable).count();	// 대출 가능한 책 수
		
		// 대출 중인 책 수 = 총 책 수 - 대출 가능한 책 수
		long borrowedBooks = totalBooks - availableBooks;
		
		// 연체된 책수
		long overDueBooks = books.values().stream()
								.filter(book -> !book.isAvailable() && book.isOverdue()).count();
		
		// 통계 정보 출력
		System.out.println("총 도서 수 : " + totalBooks + "권");
		System.out.println("대출 가능 : " + availableBooks + "권");
		System.out.println("대출 중 : " + borrowedBooks + "권");
		System.out.println("연체 : " + overDueBooks + "권");
		System.out.println("등록 회원 수 : " + members.size() + "명");
		System.out.println("등록 사서 수 : " + librarians.size() + "명");
	} 
}

















