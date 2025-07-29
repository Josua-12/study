package stream;

import java.util.Arrays;

public class IntArrayTest {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5};
		Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
		//---------------- ---------------------------
		// 스트림 객체 생성 	    요소를 하나씩 꺼내서 출력하는 메서드
		
		System.out.println();
		
		int sum = Arrays.stream(arr).sum();
		int count = (int)Arrays.stream(arr).count();
		System.out.println(sum);
		System.out.println(count);
	}
}
