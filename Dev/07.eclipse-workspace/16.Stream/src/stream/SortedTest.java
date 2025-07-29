package stream;

import java.util.Arrays;
import java.util.List;

public class SortedTest {

	public static void main(String[] args) {
		// 문자열 리스트 생성
		List<String> fruits = Arrays.asList("파인애플", "사과", "바나나", "귤");
		
		// 오름차순 정렬
		fruits.stream()
			.sorted()
			.forEach(s -> System.out.print(s + " "));
		
		System.out.println();
		
		// 문자열 길이에 따라 정렬
		fruits.stream()
			.sorted((f1, f2) -> Integer.compare(f1.length(), f2.length()))
			.forEach(s -> System.out.print(s + " "));
	}
}
