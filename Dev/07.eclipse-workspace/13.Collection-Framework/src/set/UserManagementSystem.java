package set;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * - HashSet 활용한 중복 방지 및 빠른 검색
 *   메뉴 기반 콘솔 애플리케이션 
 */
public class UserManagementSystem {

	private static Set<String> registeredUsers = new HashSet<>();
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 초기 데이터 로딩 (db에서는 SELECT * FROM users;)
		initializeUsers();
		
		while(true) {
			displayMenu();
		}
	}

	/*
	 * 유지보수성 
	 */
	private static void displayMenu() {
		System.out.println("\n" + "=".repeat(40));
		System.out.println("🏢 사용자 관리 시스템 v1.0");
		System.out.println("\n" + "=".repeat(40));
	}

	/*
	 * 대용량 데이터의 경우 페이징 처리
	 */
	private static void initializeUsers() {
		registeredUsers.add("admin");
		registeredUsers.add("user01");
		registeredUsers.add("manager");
		registeredUsers.add("developer");
		registeredUsers.add("tester");
		
		System.out.println("✅ 초기 사용자 데이터가 로드되었습니다. (총 " + registeredUsers.size() + "명)");
	}
}
