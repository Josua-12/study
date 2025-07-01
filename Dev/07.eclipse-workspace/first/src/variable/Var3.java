package variable;

public class Var3 {

	public static void main(String[] args) {
		// 1. 변수 선언, 초기화 각각 따로
		int a;  // 변수 복수개 선언 가능
		a = 1;
		System.out.println(a);
		System.out.println(); // 아무것도 입력 안하면 한줄띄움
		
		// 2. 변수 선언과 초기화를 한번에
		int b = 2;
		System.out.println(b);
		System.out.println();
		
		// 3. 여러 변수 선언과 초기화를 한번에
		int c = 3, d = 4;
		System.out.println(c);
		System.out.println(d);
	}
}
