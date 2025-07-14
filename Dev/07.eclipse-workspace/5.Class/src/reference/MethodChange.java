package reference;

public class MethodChange {
	
	public static void main(String[] args) {
		int a = 10;
		System.out.println("메서드 호출 전: a = " + a);
		changePrimitice(a);
		System.out.println("메서드 호출 후: a = " + a);
	}

	public static void changePrimitice(int x) {
		x = 20;
		// 지역변수 x는 scope이 끝나면 사라짐
	}
}
