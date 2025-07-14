package oop;

public class BankAccountMainStep1 {

	public static void main(String[] args) {
		// 계좌 정보를 변수로 관리
		String accountNumber = "123-456-789";	// 계좌번호
		String accountName = "이순신";			// 계좌주
		int balance = 10000; 					// 잔액
		
		// 계좌 개설
		System.out.println("계좌를 개설합니다.");
		System.out.println("계좌 번호: " + accountNumber);
		System.out.println("계좌주: " + accountName);
		System.out.println("초기 잔액: " + balance + "원");
		System.out.println();
		
		// 5000원입금
		balance += 5000;
		System.out.println("5000원 입금");
		System.out.println("현재 잔액: " + balance + "원");
		System.out.println();
		
		// 3000원 입금
		balance += 3000;
		System.out.println("3000원 입금");
		System.out.println("현재 잔액: " + balance + "원");
		System.out.println();
		
		// 2000원 출금
		balance -= 2000;
		System.out.println("2000원 출금");
		System.out.println("현재 잔액: " + balance + "원");
		System.out.println();
		
		// 계좌 정보 확인
		System.out.println("계좌 정보를 확인합니다.");
		System.out.println("계좌 번호: " + accountNumber);
		System.out.println("계좌주: " + accountName);
		System.out.println("현재 잔액: " + balance + "원");
		System.out.println();
		
		// 계좌 닫기
		System.out.println("계좌를 닫습니다.");
	}
}
