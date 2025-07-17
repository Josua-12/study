package encap;

public class EncapsulationTest {
	public static void main(String[] args) {
		System.out.println("=== 은행 계좌 테스트 ===");
		BankAccount account = new BankAccount("123-456-789", "이순신");        
        account.deposit(10000);      // 1. 입금, 출력
        account.withdraw(3000);      // 2. 출금, 출력
        account.withdraw(8000);      // 3. 잔액 부족 케이스, 출력
        account.printAccountInfo();        // 4. 계좌 정보 출력
        
        System.out.println("\n=== 게임 캐릭터 테스트 ===");
        System.out.println("--- 올바른 캐릭터명 테스트 ---");
        GameCharacter gameChar = new GameCharacter("TestUser01");
        System.out.println("캐릭터 '" + gameChar.getName() + "'이(가) 생성되었습니다.");
        gameChar.damage(30);
        gameChar.heal(20);
        gameChar.levelUp();
        System.out.println();
        gameChar.printCharInfo();
	}
}