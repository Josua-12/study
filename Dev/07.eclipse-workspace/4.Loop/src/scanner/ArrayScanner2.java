package scanner;

import java.util.Scanner;

/*
 * 예) 1
      2
      3
      4
      5
      출력
      5, 4, 3, 2, 1
 */
public class ArrayScanner2 {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력을 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		int[] numbers = new int[5];
		
		System.out.println("5개의 정수를 입력하세요 : ");
		// for문을 사용해서 배열의 각 인덱스에 사용자 입력한 값 저장
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = scanner.nextInt(); // 정수 입력받아 배열에 저장
		}
		
		System.out.println("출력");
		for(int i = numbers.length-1; i >= 0; i--) {
			System.out.print(numbers[i]);  // 현재 인덱스의 값 출력
			
			// 배열의 마지막 요소를 제외한 경우에만 콤마와 공백(, ) 출력
			if(i > 0) {
				System.out.print(", ");
			}
		}
	}
}
