package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import exception.BookAlreadyBorrowedException;

/*
 * 도서
 */
public class Book {
	private String isbn;			// 고유 번호
	private String title;			// 제목
	private String author;			// 저자
	private String category;		// 카테고리
	private LocalDate publishDate;		// 출판일
	private boolean available;		// 대출 가능 여부
	private String borrowedId;		// 대출자 ID
	private LocalDate borrowDate;	// 대출일
	
	
	public Book(String isbn, String title, String author, String category, LocalDate publishDate) {
		//super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.category = category;
		this.publishDate = publishDate;	// 대출 가능
		this.available = true;
		this.borrowedId = null;
		this.borrowDate = null;
	}
	
	/*
	 * 책 대출 처리
	 * memberId : 대출할 회원 Id
	 * BookAlreadyBorrowedException 이미 대출중인 경우
	 */
	public void borrowTo(String memberId) throws BookAlreadyBorrowedException {
		if(!available) {
			throw new BookAlreadyBorrowedException("이미 대출중인 책입니다 : " + title);
		}
		
		this.available = false;
		this.borrowedId = memberId;
		this.borrowDate = LocalDate.now();
	}
	
	/*
	 * 책 반납 처리
	 */
	public void returnBook() {
		this.available = true;
		this.borrowedId = null;
		this.borrowDate = null;
	}
	
	/*
	 * 연체 여부 확인 (14일 기준)
	 * return 연체되었으면 true, 아니면 false
	 * LocalDate plusDays(long daysToadd)
	 * public boolean isAfter(ChronoLocalDate other)
	 */
	public boolean isOverdue() {
		if(borrowDate == null) return false;
		return LocalDate.now().isAfter(borrowDate.plusDays(14));
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getCategory() {
		return category;
	}

	public LocalDate getPublishDate() {
		return publishDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getBorrowedId() {
		return borrowedId;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		String status = available ? "대출가능" : 
			("대출중(대출자 : " + borrowedId +", 대출일 : " + borrowDate.format(formatter)
					+ (isOverdue() ? "-- 연체됨" : "") + ")");
		return String.format("ISBN : %s, 제목 : %s, 저자 : %s, 카테고리 : %s, 출판일 : %s, 상태(대출여부) : %s", 
				isbn, title, author, category, publishDate.format(formatter), status);
	}
	
	
	
}
