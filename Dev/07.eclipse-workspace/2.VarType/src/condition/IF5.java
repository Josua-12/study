package condition;

public class IF5 {
	
	public static void main(String[] args) {
		int price = 9000;
		System.out.println("주문 금액: " + price);
		int age = 19;
		System.out.println("연령: " + age);
		int discount = 0;
		System.out.println(discount);
		
		if(price >= 10000 && age == 20){
			discount += 2000;
			System.out.println("10,000원 이상 구매, 1,000원 할인");
			System.out.println("성년, 1,000원 할인");
			System.out.println("총 할인 금액 : 2,000원 할인");
		} else if(price >= 10000) {
			discount += 1000;
			System.out.println("10,000원 이상 구매, 1,000원 할인");
			System.out.println("총 할인 금액 : 1,000원 할인");
		} else if(age == 20) {
			discount += 1000;
			System.out.println("성년, 1,000원 할인");
			System.out.println("총 할인 금액 : 1,000원 할인");
		} else {
			System.out.println("총 할인 금액 : 0원 할인");
		}
	}
	
}