package final1;

public class FinalLocalVar {
	
	public static void main(String[] args) {
		final int data1;
		data1 = 100;			// 최초 한 번만 할당(초기화) 가능
		// data1 = 10;			// 컴파일 오류
		
		final int data2 = 200;
		// data2 = 20;			// 컴파일 오류
		
		method(300);
	}

	// final 매개변수
	private static void method(final int parameter) {
		// parameter = 400;		// 컴파일 오류
		
	}
}
