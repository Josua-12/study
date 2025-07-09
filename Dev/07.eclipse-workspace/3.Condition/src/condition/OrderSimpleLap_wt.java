package condition;
/*
 * 간단한 주문 처리 시스템 
 */
public class OrderSimpleLap_wt {

	public static void main(String[] args) {
		//고객의 주문 정보
		String menuItem = "케이크";
		int quantity = 0;
		int customerMoney = 10000;
		
		System.out.println("=== 메가 커피 주문 시스템 ===");
		System.out.println("주문 메뉴 : " + menuItem);
		System.out.println("주문 수량 : " + quantity + "개");
		System.out.println("소지 금액 : " + customerMoney +"원");
		System.out.println();
		
		//메뉴별 가격 설정 (switch문)
		int menuPrice = 0;
		
		switch(menuItem) {
			case "커피":
				menuPrice = 3000;
				System.out.println("☕ 커피 선택 - 가격: " +menuPrice+ "원");
				break;
			case "라떼":
				menuPrice = 4000;
				System.out.println("🧋 라떼 선택 - 가격: " +menuPrice+ "원");
				break;	
			case "샌드위치":
				menuPrice = 5000;
				System.out.println("🥪 샌드위치 선택 - 가격: " +menuPrice+ "원");
				break;		
			case "케이크":
				menuPrice = 6000;
				System.out.println("🍰 케이크 선택 - 가격: " +menuPrice+ "원");
				break;		
			default:
				System.out.println("❌ 죄송합니다. 해당 메뉴는 없습니다.");
				System.out.println("메뉴: 커피, 라떼, 샌드위치, 케이크");
				return;
		}
		
		// 총 주문 금액 계산
		int totalPrice = menuPrice * quantity;
		System.out.println("충 주문 금액: " +totalPrice+ "원");
		
		System.out.println("\n --- 주문 처리 중 ---");
		
		//수량이 1개 이상
		if(quantity <= 0) {
			System.out.println("❌ 주문 실패: 수량을 1개 이상 선택해주세요.");
		}	//재고는 최대 10개까지만 가능
		else if(quantity > 10) {
			System.out.println("❌ 주문 실패: 재고가 부족합니다. (최대 10개)");
		}	//소지금액이 총 주문금액 이상이어야 함
		else if(customerMoney < totalPrice) {
			int shortmoney = totalPrice - customerMoney;
			System.out.println("❌ 주문 실패: 돈이 부족합니다.");
			System.out.println("필요 금액: " +totalPrice+ "원");
			System.out.println("부족 금액: " +shortmoney+ "원");
		}	//모든 조건을 만족하는 경우
		else {
			System.out.println("✅ 주문 성공!");
			
			// 거스름돈
			int change = customerMoney - totalPrice;
			System.out.println("총 금액: " +customerMoney+ "원");
			System.out.println("결재 금액: " +totalPrice+ "원");
			System.out.println("거스름돈: " +change+ "원");
			
			// 메뉴별 예상 준비시간: 커피(3분), 라떼(3분), 샌드위치(5분), 케이크(1분)
			int prepareTime = 0;
			switch(menuItem) {
				case "커피":
				case "라떼":
					prepareTime = 3;
					break;
				case "샌드위치":
					prepareTime = 5;
					break;
				case "케이크":
					prepareTime = 1;
					break;
			}
			System.out.println("예상 준비시간: " +prepareTime+ "분");
			System.out.println("주문이 완료되었습니다.");
		}
		
	}
}