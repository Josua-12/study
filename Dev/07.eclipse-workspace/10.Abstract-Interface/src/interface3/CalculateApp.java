package interface3;

public class CalculateApp {
	// 상수 정의
	private static final int NUMBER_ONE = 10;
	private static final int NUMBER_TWO = 5;

	public static void main(String[] args) {
		 
		CompleteCalc calculator = new CompleteCalc();
		testCalculator(calculator);
		calculator.description();  // 디폴트 메서드 호출
		
		int[] arr = {1, 2, 3, 4, 5};
		System.out.println(Calc.total(arr));  // 정적 메서드 사용
	}
	
	private static void testCalculator(CompleteCalc calculator) {
		System.out.println("덧셈 결과 : " + calculator.add(NUMBER_ONE, NUMBER_TWO));
		System.out.println("덧셈 결과 : " + calculator.substract(NUMBER_ONE, NUMBER_TWO));
		System.out.println("곱셈 결과 : " + calculator.times(NUMBER_ONE, NUMBER_TWO));
		System.out.println("나눗셈 결과 : " + calculator.divide(NUMBER_ONE, NUMBER_TWO));
	}
}
