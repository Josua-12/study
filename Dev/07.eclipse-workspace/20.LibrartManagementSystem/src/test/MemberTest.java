package test;

import exception.LibraryException;
import model.Member;

public class MemberTest {

	public static void main(String[] args) {
		// 1. 기본 생성 테스트
		Member member = new Member("M001", "이순신", "010-1234-4321");
		System.out.println("회원 생성 : " + member);
		
		// 2. 대출 가능 여부 테스트
		System.out.println("대출 가능 : " + member.canBorrow());
		
		// 3. 책 대출 테스트
		try {
			member.borrowBook("2025-1");
			member.borrowBook("2025-2");
			member.borrowBook("2025-3");
			System.out.println("3권 대출 완료. 현재 대출 : " + member.getBorrowCount());
			
			//4. 최대 대출 초과 테스트
			member.borrowBook("2025-4");	// 예외 발생해야 함
		} catch (Exception e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}		
		
		// 5. 반납 테스트
		boolean returned = member.returnBook("2025-1");
		System.out.println("반납 성공 : " + returned);
		System.out.println("반납 후 대출 수 : " + member.getBorrowCount());
	}
}





