package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterTest {

	public static void main(String[] args) {
		Predicate<Integer> pre = x -> x > 0; 		// 양수를 찾는 predicate
		
		List<Integer> numbers =  Arrays.asList(5, -10, -22, 0, 43, 7);
		Stream<Integer> stream = numbers.stream().filter(pre);
		stream.forEach(n -> System.out.print(n + " "));
	}
}
