

package oop;

public class BankAccountMainStep3 {

	public static void main(String[] args) {
		// BankAccountData객체 생성
		BankAccountData account = new BankAccountData();
		
		// 계좌 개설
		open(account);
		
		// 5000원입금
		deposite(account, 5000);
		
		// 3000원 입금
		deposite(account, 3000);
		
		// 2000원 출금
		withdraw(account, 2000);
		
		// 계좌 정보 확인
		showStatus(account);
		
		// 계좌 닫기
		close(account);
		
	}

	static void close(BankAccountData account) {
		System.out.println("계좌를 닫습니다.");
	}

	static void showStatus(BankAccountData account) {
		System.out.println("계좌 정보를 확인합니다.");
		System.out.println("계좌 번호: " + account.accountNumber);
		System.out.println("계좌주: " + account.accountName);
		System.out.println("현재 잔액: " + account.balance + "원");		
	}

	// 출금 메서드
	static void withdraw(BankAccountData account, int amount) {
		account.balance -= amount;
		System.out.println(amount + "원 출금");
		System.out.println("현재 잔액: " + account.balance + "원");
	}

	// 입금 메서드
	static void deposite(BankAccountData account, int amount) {
		account.balance += amount;
		System.out.println(amount + "원 입금");
		System.out.println("현재 잔액: " + account.balance + "원");
	}

	// 계좌 개설 메서드
	static void open(BankAccountData account) {
		System.out.println("계좌를 개설합니다.");
		System.out.println("계좌 번호: " + account.accountNumber);
		System.out.println("계좌주: " + account.accountName);
	}
}



















