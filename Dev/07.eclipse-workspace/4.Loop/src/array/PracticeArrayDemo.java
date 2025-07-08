package array;

import java.util.Scanner;


public class PracticeArrayDemo {

	//Scanner 객체를 전역으로 선언하여 모든 메서드에서 사용 가능
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("=== 배열 프로그램 ===");
		System.out.println("데이터 처리 프로그램입니다. 다양한 기능을 제공합니다.");
		System.out.println();
		
		while(true) {
			// 메뉴 출력
			displayMenu();
			
			// 사용자 선택에 따른 기능 실행
			int choice = scanner.nextInt();
			
			switch(choice) {
				case 1:
					basicArrayOutput();
					break;
				case 0:
					System.out.println("프로그램을 종료합니다.");
					return;
				default:
					System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
			}
		}
	}

	private static void basicArrayOutput() {
		System.out.println("=== 배열 기본 출력 ===");
		System.out.print("배열 크기를 입력하세요 : ");
		int count = scanner.nextInt();
		int[][] scores = new int[count][3];
		
		
		//과목명 저장할 배열
		String[] subjects = {"1번째", "2번째", "3번째"};
		
		// 4명의 학생에 대한 반복
		for(int i = 0; i < count; i++) {
			System.out.println((i+1) + "개의 정수를 입력하세요 : ");
			
			// 3개 과목에 대해 반복
			for(int j = 0; j < 3; j++) {
				System.out.print(subjects[j] + " 수 : ");
				// 2차원 배열 점수 저장
				scores[i][j]= scanner.nextInt();
				System.out.print(scores[j]);  // 현재 인덱스의 값 출력
				
				// 배열의 마지막 요소를 제외한 경우에만 콤마와 공백(, ) 출력
				if(j < scores.length-1) {
					System.out.print(", ");				
				}
			}
		}
	}
	
	private static void displayMenu() {
		System.out.println("\n=== 메뉴 선택 ===");		
		System.out.println("1. 배열 기본 출력 (순차 출력)");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 : ");
	}
}
