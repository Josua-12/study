package condition;
/*
 * 간단한 주문 처리 시스템
 */
public class OrderSimpleLab2_wt {

	public static void main(String[] args) {
		// 고객의 주문 정보
		String menuItem = "샌드위치";
		int quantity = 2;
		int customerMoney = 15000;
		// 메뉴별 가격 설정 (switch문)
		int menuPrice = 0;
		
		
		
		System.out.println("=== 메가커피 주문 시스템 ===");
		System.out.println("메뉴를 선택하세요: " + menuItem);
		System.out.println("수량을 입력하세요: " + quantity + "개");
		System.out.println("소지 금액을 입력하세요: " + customerMoney + "원");
		System.out.println();
		
		
		
		if(quantity > 10) { 
			System.out.println("✖️ 주문 실패: 재고가 부족합니다. (최대 10개)");
		} 
		else if(quantity < 1) {
			System.out.println("✖️ 주문 실패: 수량을 1개 이상 선택해주세요.");
		} else if(!menuItem.equals("커피") && !menuItem.equals("라떼") &&
				!menuItem.equals("샌드위치") && !menuItem.equals("케이크")) {
			System.out.println("✖️ 죄송합니다. 해당 메뉴는 없습니다.");
		} 
		
		else {
			System.out.println("✅ 주문 성공");
			int waitingTime = 0;
			switch (menuItem) {
			case "커피" :
				menuPrice = 3000;
				waitingTime = 3;
				break;
			case "라떼" :
				menuPrice = 4000;
				waitingTime = 3;
				break;
			case "샌드위치" :
				menuPrice = 5000;
				waitingTime = 5;
				break;
			case "케이크" :
				menuPrice = 6000;
				waitingTime = 1;
				break;
			default :
				System.out.println("❌ 죄송합니다. 해당 메뉴는 없습니다.");
				System.out.println("메뉴 : 커피, 라떼, 샌드위치, 케이크");
				return;
			}
			// 총 주문 금액 계산totalPrice 
			int totalPrice = menuPrice * quantity;
			
			
				System.out.println("총 금액 : " + totalPrice + "원");
				
			
		}
		
		
		
		
		
		
		
		
		
	}
}