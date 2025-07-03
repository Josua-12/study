package condition;
/*
 * switch-case 조건문
 * 학생 학년 등급에 따른 쿠폰 발급 (금액)
 * 자바 14 새로운 switch문
 */
public class Switch4 {
	
	public static void main(String[] args) {
		int grade = 3;
		
		
		
		int coupon = switch(grade) {
			case 1 -> 1000;
			case 2 -> 2000;
			case 3 -> 3000;
			default -> 500;
		};
		
		System.out.println("발급받은 쿠폰 : " + coupon);
	}
}
