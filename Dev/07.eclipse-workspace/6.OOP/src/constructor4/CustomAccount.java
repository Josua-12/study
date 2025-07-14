package constructor4;

public class CustomAccount {

	String accountNumber;
	String ownerName;
	long balance;
	
	// 매개변수가 있는 생성자를 정의하면 컴파일러가 기본 생성자를 자동으로 생성하지 않음
	public CustomAccount(String accountNumber, String ownerName, long balance) {
		//super();
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	void printInfo() {
		System.out.println("계좌 번호: " + accountNumber + 
						   ", 예금주: " + ownerName +
						   ", 잔액: " + balance + "원");
		
	}
	
}
