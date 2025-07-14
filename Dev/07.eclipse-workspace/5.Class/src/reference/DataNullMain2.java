package reference;

public class DataNullMain2 {
	
	public static void main(String[] args) {
		Data data = null;		// 지역변수 값이 null인 경우
		data.value = 10;		// NullPointerException 예외 발생
		System.out.println("data = " + data.value);
	}
}

