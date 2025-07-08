package scanner;

import java.util.Scanner;

/*
 * 예) 입력받을 숫자의 개수를 입력하세요 : 3
 * 	   1
       2
       3
       출력
       입력한 정수의 합계 : 6
       입력한 정수의 평균 : 2.0
 */
public class ArrayScanner4 {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력을 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		
		System.out.println("입력받을 숫자의 개수를 입력하세요");
		int count = scanner.nextInt();
		int[] numbers = new int[count];
		int sum = 0;
		
		System.out.println(count + "개의 정수를 입력하세요 : ");
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = scanner.nextInt();
			sum += numbers[i];
		}
		// 평균
		double average = (double) sum / numbers.length; // numbers.length = 5
		
		// 출력
		System.out.println("입력한 정수의 합계 : " + sum);
		System.out.println("입력한 정수의 평균 : " + average);
		
		scanner.close();
	}
}

