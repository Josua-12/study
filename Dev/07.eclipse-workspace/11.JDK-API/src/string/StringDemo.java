package string;

public class StringDemo {

	public static void main(String[] args) {
		String str1 = new String("abc");
		String str2 = new String("abc");
		
		System.out.println(str1 == str2);	// 인스턴스가 매번 주소번지 새로 생성
		System.out.println(str1.equals(str2));
		
		String str3 = "abc";	// 문자열 abc는 상수 풀에 저장되어 있음 => 주소값이 같음
		String str4 = "abc";
		
		System.out.println(str3 == str4);
		System.out.println(str3.equals(str4));
	}
}
