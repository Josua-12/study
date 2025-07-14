package method;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n ==== 간단한 계산기 ====");
			System.out.print("첫 번째 숫자를 입력하세요 : ");
			double num1 = scanner.nextDouble();
			
			System.out.print("연산자를 입력하세요 (+, - , *, /) : ");
			String op = scanner.next();
			
			System.out.print("두 번째 숫자를 입력하세요 : ");
			double num2 = scanner.nextDouble();
			
			
			
			double result = 0;
			boolean valid = true;
			
			// 연산자에 따른 메서드 분기
			switch(op) {
				case "+":
					result = add(num1, num2);
					break;
				case "/":
					if(num2 != 0) {
						result = divide(num1, num2);
					}
					break;
				default: 
					System.out.println("❌ 지원하지 않는 연산자입니다.");
					valid = false;
			}
			if(valid) {
				printResult(num1, op, num2, result); // 출력 메서드 호출
			}
			
			if(op.equals("/") && num2 == 0) {
				System.out.println("❌ 0으로 나눌 수 없습니다.");
				
				
				System.out.print("계속 하시겠습니까? (y/n)");
				String cont = scanner.next();
				if(!cont.equalsIgnoreCase("y")) {
					break;
				} else {
					continue;
				}
			}
			
			
			
		}
		
		scanner.close();
		System.out.println("👏 계산기를 종료합니다.");
	}

	
	private static double divide(double num1, double num2) {
		
		return num1 / num2;
	}


	private static void printResult(double a, String op, double b, double result) {
		System.out.printf("✅ 계산 결과 : %.2f %s %.2f = %.2f\n", a, op, b, result);
	}

	private static double add(double num1, double num2) {
		
		return num1 + num2;
	}
}
