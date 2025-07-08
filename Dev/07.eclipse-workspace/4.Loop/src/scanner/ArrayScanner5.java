package scanner;

import java.util.Scanner;

public class ArrayScanner5 {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("입력받을 숫자의 개수를 입력하세요 : ");
		// 배열의 크기를 동적으로 할당받을 변수
		int n = scanner.nextInt();
		
		//사용자가 입력한 개수만큼 배열 동적 할당
		int[] numbers = new int[n];
		
		// 최솟값과 최대값을 저장할 변수 선언
		int minNumber, maxNumber;
		
		// 배열에 값 저장
		for(int i = 0; i < n; i++) {
			numbers[i] = scanner.nextInt();
		}
		
		// 최솟값과 최대값 초기화 - 첫번째 요소로 설정
		minNumber = maxNumber = numbers[0];
		
		// 두번째 요소부터 마지막 요소까지 비교하여 최솟값, 최대값
		for(int i =1; i < n; i++) {
			// 현재 요소가 최솟값보다 작으면 최솟값 업데이터
			if(numbers[i] < minNumber) {
				minNumber = numbers[i];
			}
			
			// 현재 요소가 최댓값보다 크면 최대갓 업데이트
			if(numbers[i] > maxNumber) {
				maxNumber = numbers[i];
			}
		}
		// 출력
		System.out.println("가장 작은 정수 : " + minNumber);
		System.out.println("가장 큰 정수 : " + maxNumber);
		
		scanner.close();
	}
}
