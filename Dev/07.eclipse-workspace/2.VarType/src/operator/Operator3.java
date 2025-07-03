package operator;

/*
 * * 증감 연산자
 *    - 1만큼 증가시키거나 1만큼 감소시킴
 *    - 값을 1씩 증가하거나 1씩 감소할 때가 아주 많기 때문에 이런 편의 기능
 *    	제공함
 */
public class Operator3 {

	public static void main(String[] args) {
		int a = 0;
		
		a = a + 1;
		System.out.println("a = " + a);
		a = a + 1;
		System.out.println("a = " + a);
		
		// 증감 연산자
		++a; // a = a + 1;와 같음
		System.out.println("a = " + a);
		++a; // a = a + 1;와 같음
		System.out.println("a = " + a);
		
		// 전위, 후위 증감 연산자
		a = 1;
		int b = 0;
		b = ++a; // a의 값을 증가 시키고, 그 결과를 b에 대입(전위)
		System.out.println("a = " + a + ", b = " + b );
		
		a = 1;
		b = 0;
		b = a++; // a의 현재 값을 b에 먼저 대입하고, 그 후 a의 값을 증가시킴
		System.out.println("a = " + a + ", b = " + b );
		
	}
}
