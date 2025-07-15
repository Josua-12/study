package access;

public class BankAccessMain {

	public static void main(String[] args) {
		BankAccess access = new BankAccess();
		access.deposit(10000);
		access.withdraw(3000);
//		access.withdraw(3000);
//		access.withdraw(5000);
		System.out.println("잔액 = " + access.getBalance());
	}
}
