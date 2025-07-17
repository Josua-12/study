package memory;
/*
 *  - stack 메모리에서 메소드 프레임 생성/제거
 *  - 메소드 호출 시 매개변수 전달
 */
public class JVMStack2 {
	public static void main(String[] args) {
		System.out.println("main start");
		method1();
		System.out.println("main end");
	}

	static void method1() {
		System.out.println("method1 start");
		Data data = new Data(10);
		method2(data);
		System.out.println("method1 end");		
	}

	static void method2(Data data2) {
		System.out.println("method2 start");
		System.out.println("data.value = " + data2.getValue());
		System.out.println("method2 end");		
	}
}