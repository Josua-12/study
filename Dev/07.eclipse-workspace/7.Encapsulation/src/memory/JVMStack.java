package memory;
/*
 *  - stack 메모리에서 메소드 프레임 생성/제거
 *  - 메소드 호출 시 매개변수 전달
 */
public class JVMStack {
	public static void main(String[] args) {
		System.out.println("main start");
		method1(10);
		System.out.println("main end");
	}

	static void method1(int m1) {
		System.out.println("method1 start");
		int cal = m1 * 2;
		method2(cal);
		System.out.println("method1 end");		
	}

	static void method2(int m2) {
		System.out.println("method2 start");
		System.out.println("method2 end");		
	}
}