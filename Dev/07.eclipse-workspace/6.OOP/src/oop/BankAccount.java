package oop;

public class BankAccount {
	// 필드 (데이터)
	String accountNumber = "123-456-789";	// 계좌번호
	String accountName = "이순신";			// 계좌주
	int balance = 10000; 					// 잔액
	
	// 계좌 개설 메서드
	void open() {
		System.out.println("계좌를 개설합니다.");
		System.out.println("계좌 번호: " + accountNumber);
		System.out.println("계좌주: " + accountName);
	}
	
	// 입금 메서드
	void deposite(int amount) {
		balance += amount;
		System.out.println(amount + "원 입금");
		System.out.println("현재 잔액: " + balance + "원");
	}
	
	// 출금 메서드
	void withdraw(int amount) {
		balance -= amount;
		System.out.println(amount + "원 출금");
		System.out.println("현재 잔액: " + balance + "원");
	}
		
	void showStatus() {
		System.out.println("계좌 정보를 확인합니다.");
		System.out.println("계좌 번호: " + accountNumber);
		System.out.println("계좌주: " + accountName);
		System.out.println("현재 잔액: " + balance + "원");		
	}
	
	void close() {
		System.out.println("계좌를 닫습니다.");
	}

}
