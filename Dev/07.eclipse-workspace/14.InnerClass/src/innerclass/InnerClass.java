package innerclass;

class OutClass { 		  			// 외부 클래스
	private int num = 10;		  	// 외분 클래스 private 변수
	private static int sNum = 20; 	// 외부 클래스 정적 변수
	
	private InClass inClass;	 	// 내부 클래스형 참조 변수 선언
	public OutClass() {
		inClass = new InClass();  	// 외부 클래스의 디폴트 생성자 - 외부 클래스가 생성된 후 내부 클래스 생성
	}
	
	
	class InClass {					// 인스턴스 내부 클래스
		int inNum = 100;			// 내부 클래스의 인스턴스 변수
		static int sInNum = 200;	// 인스턴스 내부 클래스에 정적 변수
		
		void inTest() {
			System.out.println("외부 클래스의 인스턴스 변수 = " + num);// 외부 클래스의 인스턴스 변수
			System.out.println("외부 클래스의 정적 변수 = " + sNum);  // 외부 클래스의 정적 변수
			System.out.println("인스턴스 내부 클래스의 인스턴스 변수" + inNum); // 인스턴스 내부 클래스의 인스턴스 변수
			System.out.println("인스턴스 내부 클래스의 정적 변수" + sInNum); //인스턴스 내부 클래스의 정적 변수
		}
		
		static void sTest() {
			System.out.println("인스턴스 내부 클래스의 정적 메서드 호출");
		}
	}
	public void usingClass() {
		inClass.inTest();
	}
}


public class InnerClass {
	
	public static void main(String[] args) {
		// 인스턴스 내부 클래스 생성하기(1)
		OutClass outClass = new OutClass();
		System.out.println("외부클래스 이용하여 내부클래스 기능 호출");
		outClass.usingClass();
		System.out.println();
		
		System.out.println("인스턴스 내부 클래스의 정적 변수 직접 호출 : " +
						OutClass.InClass.sInNum);
		OutClass.InClass.sTest();
		System.out.println();
		
		// 인스턴스 내부 클래스 생성하기(2)
		OutClass.InClass inClass = outClass.new InClass();
		inClass.inTest();
	}
}
