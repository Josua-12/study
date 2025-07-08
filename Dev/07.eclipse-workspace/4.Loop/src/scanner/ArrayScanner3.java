package scanner;

import java.util.Scanner;

/*
 * 예) 1
       2
       3
       4
       5
       출력
       입력한 정수의 합계 : 15
       입력한 정수의 평균 : 3.0
 */
public class ArrayScanner3 {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력을 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		int[] numbers = new int[5];
		
		// 합계 저장할 변수
		int sum = 0;
		// 평균 저장할 변수
		double average;
		System.out.println("5개의 정수를 입력하세요 : ");
		// for문을 사용해서 배열의 각 인덱스에 사용자 입력한 값 저장
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = scanner.nextInt(); // 정수 입력받아 배열에 저장
			sum += numbers[i]; // 입력 받은 값을 합계에 누적
		}
		// 평균
		average = (double) sum / numbers.length; // numbers.length = 5
		
		// 출력
		System.out.println("입력한 정수의 합계 : " + sum);
		System.out.println("입력한 정수의 평균 : " + average);
		
		scanner.close();
		
	}
}
