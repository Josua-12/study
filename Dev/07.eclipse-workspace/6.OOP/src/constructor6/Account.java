package constructor6;

public class Account {

	String accountNumber;
	String ownerName;
	long balance;
	
	
	public Account(String accountNumber, String ownerName, long balance) {
		//super();
		this.accountNumber = accountNumber;
		this.ownerName = ownerName;
		this.balance = balance;
	}


	public Account(String accountNumber, String ownerName) {
		//super();
		this(accountNumber, ownerName, 0);
	}


	public Account(String ownerName, long balance) {
		//super();
		this("AUTO-" + System.currentTimeMillis(), ownerName, balance);
	}
	
	void printInfo() {
		System.out.println("계좌 번호: " + accountNumber + 
				   		   ", 예금주: " + ownerName +
				   		   ", 잔액: " + balance + "원");
	}
	
	void deposit(long amount) {
		balance += amount;
		System.out.println(amount + "원 입금완료. 잔액 : " + balance + "원");
		
	}
	
}
