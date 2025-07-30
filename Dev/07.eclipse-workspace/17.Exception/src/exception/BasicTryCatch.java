package exception;

import java.util.Scanner;

public class BasicTryCatch {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=====================");
		System.out.println("숫자를 입력하고 1000을 나눈 결과를 보여줍니다.");
		System.out.println(" ('quit') 입력시 종료)\n");
		
		int count = 1;
		try { 
			while(true) {
				System.out.println(" 테스트 " + count + "회차");
				System.out.print("숫자를 입력하세요 : ");
				
				String input = scanner.nextLine();
				
				// 종료 조건
				if("quit".equalsIgnoreCase(input.trim())) {
					System.out.println("프로그램 종료!");
					break;
				}
				
				// 예외 처리 try~catch 블록
				try {
					// 문자열 => 정수 변환
					int number = Integer.parseInt(input);
					System.out.println("숫자 변환 성공 : " + input + " => " + number);
					
					// 나눗셈 연산
					int result = 1000 / number;
					System.out.println("계산 완료 : 1000 ÷ " + number + " = " + result);
					
				} catch (NumberFormatException e) {
					// 숫자가 아닌 문자열 입력 시 발생하는 예외 처리
					System.out.println("숫자 변환 실패 : " + input + "은 숫자가 아닙니다.");
					System.out.println("숫자만 입력해주세요 (예: 100, -50)");
					System.out.println("예외 상세 :" + e.getClass().getSimpleName());
				} catch (ArithmeticException e) {
					// 0으로 나누기 시도 시 발생하는 예외 처리
					System.out.println("계산 오류 : 0으로 나눌 수 없습니다.");
					System.out.println("0이 아닌 숫자를 입력해주세요");
					System.out.println("예외 상세 :" + e.getClass().getSimpleName());
				} catch (IllegalArgumentException | NullPointerException e) {
					// 
				} catch (Exception e) {
					// 
				}

				count++;	
				System.out.println("예외가 발생해도 프로그램이 계속 실행됨 확인함");
			}
		} catch(Exception e) {
			System.err.println("프로그램 실행 중 오류 발생 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			// finally 블록 : 프로그램 종료 시 반드시 실행되어야 하는 자원 해제
			if(scanner != null) {
				scanner.close();
				System.out.println("Scanner 자원 안전하게 해제되었습니다.");
			}
			System.out.println("예외가 발생해도 프로그램이 계속 실행됨 확인했습니다.");
		}
	}
}  
