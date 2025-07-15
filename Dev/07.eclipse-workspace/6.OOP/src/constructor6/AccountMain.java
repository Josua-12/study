package constructor6;

public class AccountMain {

	public static void main(String[] args) {
		System.out.println("=== 생성자 오버로딩 확인 ===");
		
		// 첫번째 생성자(모든 정보 제공)
		System.out.println("1. 모든 정보로 계좌 생성");
		Account acc1 = new Account("1001", "이순신", 50000);
		acc1.printInfo();
		
		// 두번째 생성자(this() 사용, 잔액 0)
		System.out.println("\n2. 잔액 0으로 계좌 생성 (this() 사용)");
		Account acc2 = new Account("1002", "신사임당");
		acc2.printInfo();
		acc2.deposit(30000);
		
		// 세번째 생성자 : 계좌번호 자동 생성
		System.out.println("\n3. 계좌번호 자동 생성");
		Account acc3 = new Account("이방원", 100000);
		acc3.printInfo();
	}
}
