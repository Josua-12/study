package scope;

public class ScopeTest {

	public static void main(String[] args) {
		System.out.println("=== java 스코프 데모 ===");
		
		System.out.println("1. 사용자 인증 시스템");
		System.out.println("-------------------");
		userAuthenticationDemo();
		
		System.out.println("2. 쇼핑몰 재고 관리");
		System.out.println("-------------------");
		inventoryDemo();
	}

	private static void inventoryDemo() {
		int totalStock = 50;	// 전체 재고
		int soldItem = 0;		// 판매된 아이템 수		
		
		System.out.println("초기 재고 : " + totalStock);
		
		// 3시간 동안 판매 시뮬레이션
		for(int hour = 9; hour <= 11; hour++) {  // hour는 for문 블록 내에서만 존재
			int hourlyOrders = 15;		// 시간 당 주문 수
			System.out.println(hour + "시 : " + hourlyOrders + "개 주문");
			
			//재고 확인 및 판매 처리
			if(totalStock >= hourlyOrders) {	// 외부 변수 totalStock 접근 가능
				totalStock -= hourlyOrders;		// 재고 차감
				soldItem += hourlyOrders;		// 판매 수량 증가
				System.out.println(" 판매 완료! 남은 재고 : " + totalStock);
			} else {
				System.out.println(" 재고 부족! 현재 재고 : " + totalStock);
			}
		}  // hour, hourlyOrders 변수 소멸
		
		System.out.println("총 판매량 : " + soldItem);
		System.out.println("남은 재고 : " + totalStock);
	}

	private static void userAuthenticationDemo() {
		String userName = "admin";
		boolean isAuthenticated = false; // 인증 상태
		
		if(userName.equals("admin")) {  // if 블록 시작
			String password = "1234";  // password 변수는 if 블록 내부에서만 사용 가능
			System.out.println("인증 시도 : " + userName);
			
			if(password.equals("1234")) {  // 중첩된 if 블록 시작
				isAuthenticated = true;	   // 외부 변수 접근 가능
				System.out.println("로그인 성공!");
			}
			// password 변수는 여기서 소멸
		}
		if(isAuthenticated) {
			System.out.println("메인 페이지로 이동");
		} else {
			System.out.println("로그인 실패");  // 비밀번호 틀리면 로그인 실패 뜸
		}
	}  
}
