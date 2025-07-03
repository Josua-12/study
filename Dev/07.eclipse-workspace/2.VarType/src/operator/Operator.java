package operator;

public class Operator {

	public static void main(String[] args) {
		// 문자열과 문자열 더하기
		String result1 = "hello " + "java!";
		System.out.println(result1);
		
		String s1 = "string1";
		String s2 = "string2";
		String result2 = s1 + s2;
		System.out.println(result2);
		
		// 문자열과 숫자 더하기
		// => 숫자를 문자열로 변경한 다음에 서로 더함
		String result3 = "a + b = " + 10;
		System.out.println(result3);
		
		String str = "a + b = ";
		String result4 = str + 20;
		System.out.println(result4);
	}
}
