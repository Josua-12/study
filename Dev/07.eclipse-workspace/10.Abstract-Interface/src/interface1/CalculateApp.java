package interface1;

public class CalculateApp {
	// 상수 정의
	private static final int NUMBER_ONE = 10;
	private static final int NUMBER_TWO = 10;

	public static void main(String[] args) {
		
		CompleteCalc calculator = new CompleteCalc();
		testCalculator(calculator);
	}
	
	private static void testCalculator(CompleteCalc calculator) {
		System.out.println("덧셈 결과 : " + calculator.add(NUMBER_ONE, NUMBER_TWO));
		System.out.println("덧셈 결과 : " + calculator.substract(NUMBER_ONE, NUMBER_TWO));
		System.out.println("곱셈 결과 : " + calculator.times(NUMBER_ONE, NUMBER_TWO));
		System.out.println("나눗셈 결과 : " + calculator.divide(NUMBER_ONE, NUMBER_TWO));
	}
}
