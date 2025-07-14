

package oop;

public class BankAccountMainStep4 {

	public static void main(String[] args) {
		// BankAccountData객체 생성
		BankAccount account = new BankAccount();
		
		// 계좌 개설
		account.open();
		
		// 5000원입금
		account.deposite(5000);
		
		// 3000원 입금
		account.deposite(3000);
		
		// 2000원 출금
		account.withdraw(2000);
		
		// 계좌 정보 확인
		account.showStatus();
		
		// 계좌 닫기
		account.close();
		
	}

	
	

	

	

	
}



















