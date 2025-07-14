package clazz;

public class ShopStart2 {

	public static void main(String[] args) {
		//배열을 사용하여 여러 상품 정보를 관리
		String[] productName = {"아이폰 17", "나이키 에어맥스", "Galaxy Book5 Pro", "스타벅스 텀블러"};
		int[] productPrice = {1200000, 150000, 1900000, 30000};
		int[] productStock = {50, 30, 20, 100};
		String[] productCategory = {"전자제품", "신발", "전자제품", "생활용품"};
		
		System.out.println("=== 쇼핑몰 상품 정보(배열 사용) ===");
		//반복문으로 모든 상품 정보 출력
		for(int i = 0; i < productName.length; i++) {
			System.out.println("상품명: " + productName[i] +
							   " | 가격: " + productPrice[i] + "원" +
							   " | 재고: " + productStock[i] + "개" +
							   " | 카테고리: " + productCategory[i]);
		}			
	}
}
