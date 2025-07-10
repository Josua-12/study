package clazz;

public class ShopStart3 {

	public static void main(String[] args) {
		// 클래스를 사용하여 상품 객체 생성
		Product iphone;
		iphone = new Product();		// 새로운 상품 생성(메모리 주소: 0x001)
		
		// 아이폰 상품 정보 설정
		iphone.name = "아이폰 17";
		iphone.price = 1200000;
		iphone.stock = 50;
		iphone.category = "전자제품";
		
		// 신발 상품 생성과 변수 선언
		Product sneakers = new Product(); // 새로운 상품 생성 (메모리 주소: 0x002)
		
		// 신발 상품 정보 설정
		sneakers.name = "나이키 에어맥스";
		sneakers.price = 150000;
		sneakers.stock = 30;
		sneakers.category = "신발";
		
		// 클래스를 사용하면 관련 데이터가 하나로 묶여서 관리가 쉬워짐
		System.out.println("=== 쇼핑몰 상품 정보(배열 사용) ===");
		System.out.println("상품명: " + iphone.name + 
						   " | 가격: " + iphone.price + "원" + 
						   " | 재고: " + iphone.stock + "개" +  
						   " | 카테고리: " + iphone.category);
		
		System.out.println("상품명: " + sneakers.name + 
					      " | 가격: " + sneakers.price + "원" + 
					      " | 재고: " + sneakers.stock + "개" +  
					      " | 카테고리: " + sneakers.category);
		
		System.out.println(iphone);
		System.out.println(sneakers);
	}
}
