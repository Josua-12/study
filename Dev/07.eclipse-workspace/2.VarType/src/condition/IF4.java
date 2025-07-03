package condition;

import java.text.NumberFormat;
import java.util.Locale;

/*
 * * 온라인 쇼핑몰 할인 시스템 개발
 * 		- 조건
 * 			- 한 사용자가 어떤 상품을 구매할 때, 할인 조건은 다음과 같음
 * 				- 아이템 가격이 10000원 이상일 때, 1000원 할인
 * 				- 나이가 20살일 때, 1000원 할인
 * 
 * 				=> 한 사용자가 동시에 여러 할인을 받을 수 있음
 * 		- 출력 결과 예시
 * 			- 10000원 이상 구매, 1000원 할인
 * 			- 성년 1000원 할인
 * 			- 총 할인 금액 : 2000원 할인
 */

public class IF4 {

	public static void main(String[] args) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
		int buyingItem = 10000;
		System.out.println("구매 금액 : " + numberFormat.format(buyingItem) + "원");
		int customerAge = 19;
		System.out.println("나이 : " + numberFormat.format(customerAge)+ "세");
		int discount = 1000;
		
		if (buyingItem >= 10000) {
			buyingItem = buyingItem -discount;
			System.out.println("10,000원 이상 구매, 1,000원 할인");
		} 
		if (customerAge == 20) {
			buyingItem = buyingItem -discount;
			System.out.println("성년 1,000원 할인");
		}
		if (buyingItem >= 10000 && customerAge == 20) {
			System.out.println("총 할인 금액 : 2,000원 할인");
		} else if (buyingItem >= 10000 || customerAge == 20) {
			System.out.println("총 할인 금액 : 1,000원 할인");
		}
		
		
	}
}
