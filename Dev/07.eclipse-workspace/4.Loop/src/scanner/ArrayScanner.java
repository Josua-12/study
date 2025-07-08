package scanner;

import java.util.Scanner;

/*
 * 예) 1
      2
      3
      4
      5
      출력
      1, 2, 3, 4, 5
 */
public class ArrayScanner {

	public static void main(String[] args) {
		// 콘솔에서 사용자 입력을 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		int[] numbers = new int[5];
		
		System.out.println("5개의 정수를 입력하세요 : ");


		
		System.out.println("출력");
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i]);  // 현재 인덱스의 값 출력
			
			// 배열의 마지막 요소를 제외한 경우에만 콤마와 공백(, ) 출력
			if(i < numbers.length-1) {
				System.out.print(", ");
				
				
			}
		}
	}
}
