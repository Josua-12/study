package method;

import java.util.Scanner;

public class MethodTraining {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// 프로그램 시작 메시지
		printHeader();
		
		// 메뉴 선택(반복문)
		while(true) {
			// 문제 목록 출력
			problemList();
			
			
			System.out.print("풀어볼 문제 번호를 선택하세요 (0: 종료): ");
			int choice = scanner.nextInt();
			
			// 종료 조건
			if(choice == 0) {
				System.out.println("수고하셨습니다! 취업 성공하세요!!");
				break;
			}
			
			
			//선택한 문제 실행
			solveProblem(choice, scanner);
			if(choice ==1) {
				continue;
			}
		}
		
		//scanner.close();
	}
	public static void solveProblem(int choice, Scanner scanner) {
		System.out.println("=== 문제 1: 최댓값 찾기 ===");
		System.out.println("여러 개의 숫자를 입력하여 최댓값을 찾아보세요.");
		System.out.print("숫자 개수를 입력하세요 : ");
		int number = scanner.nextInt();
		
		// 배열 동적 생성
		int[] numbers = new int[number];
		
		for(int i = 0; i < number; i++) {
			System.out.print((i + 1) + "번째 숫자 : ");
			numbers[i] = scanner.nextInt();		
		}
		int maxNumber;
		
		// 최솟값과 최대값 초기화 - 첫번째 요소로 설정
		maxNumber = numbers[0];
		
		// 두번째 요소부터 마지막 요소까지 비교하여 최솟값, 최대값
		for(int i =1; i < number
				; i++) {
			// 현재 요소가 최댓값보다 크면 최대갓 업데이트
			if(numbers[i] > maxNumber) {
				maxNumber = numbers[i];
			}
		}
		// 출력
		System.out.println("최댓값 : " + maxNumber);
		
		scanner.close();
	}	
	
	public static void problemList() {
		System.out.println("\n=== 문제 목록 ===");
		System.out.println("1. 최대값 찾기(배열 없이)");
		System.out.println("0. 프로그램 종료");
		System.out.println();
		
	}
	
	/*
	 * 프로그램 헤더
	 */
	public static void printHeader() {
		System.out.println("==============================");
		System.out.println("          메서드 훈련            ");
		System.out.println("           면접 대비             ");
		System.out.println("==============================");
	}
}
