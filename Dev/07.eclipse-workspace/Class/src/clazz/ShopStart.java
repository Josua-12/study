package clazz;

public class ShopStart {

	public static void main(String[] args) {
		//첫번째 상품 정보를 개별 변수로 관리
		String product1Name = "아이폰 17";
		int product1Price = 1200000;
		int product1Stock = 50;
		String product1Category = "전자제품";
		
		//두번째 상품 정보를 개별 변수로 관리
		String product2Name = "나이키 에어맥스";
		int product2Price = 150000;
		int product2Stock = 30;
		String product2Category = "신발";
		
		//상품 정보 출력
		System.out.println("=== 쇼핑몰 상품 정보 ===");
		System.out.println("상품명: " + product1Name + " | 가격: " + product1Price + "원 | 재고: " + product1Stock + "개 | 카테고리: "+ product1Category);
		System.out.println("상품명: " + product2Name + " | 가격: " + product2Price + "원 | 재고: " + product2Stock + "개 | 카테고리: "+ product2Category);
	}
}
