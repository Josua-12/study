package variable;

public class Var {

	public static void main(String[] args) {
		// 정수 
		//byte b = 127;  // 'byte'는 -128 ~ 127까지만 assign 가능
		
		// byte 타입의 비트 수와 범위
		int bitCount = 8;
		System.out.println("비트 수 : " + bitCount);
		System.out.println("표현 가능한 값의 개수(byte) : " + (int)Math.pow(2, bitCount)
					+ "개");
		
		int maxValue = (int)Math.pow(2, bitCount-1) -1;
		System.out.println("최댓값: " + maxValue);
		
		int minValue = -(int)Math.pow(2, bitCount-1);
		System.out.println("최솟값: " + minValue);
		
		//short s = 32767; // -32,768 ~ 32,767
		//int i = 2147483647; // 약 20억
		//long l = 9223372036854775807L;
		
		//실수
		//float f = 10.0F;
		//double d = 10.0;
	}
}
