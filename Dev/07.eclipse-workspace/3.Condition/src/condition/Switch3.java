package condition;
/*
 * if-else if-else 조건문
 * 학생 학년 등급에 따른 쿠폰 발급 (금액)
 * 
 */
public class Switch3 {
	
	public static void main(String[] args) {
		int grade = 2;
		
		
		int coupon ;
		switch(grade) {
			case 1:
				coupon = 1000;
				break;
			case 2: 
			case 3:
				coupon = 3000;
				break;
			default:
				coupon = 500;
		}
		
		System.out.println("발급받은 쿠폰 : " + coupon);
	}
}
