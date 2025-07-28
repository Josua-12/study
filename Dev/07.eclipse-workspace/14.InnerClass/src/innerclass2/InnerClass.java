package innerclass2;

class OutClass { 		  			// 외부 클래스
	private int num = 10;		  	// 외분 클래스 private 변수
	private static int sNum = 20; 	// 외부 클래스 정적 변수
	
	static class InstaticClass {	// 정적 내부 클래
		int inNum = 100;			// 정적 내부 클래스의 인스턴스 변수
		static int SInNum = 200;	// 정적 내부 클래스의 정적 변수
		
		void inTest() {				// 정적 내부 클래스의 일반 메서드
			//num += 10;
			System.out.println("내부 클래스의 인스턴스 변수 사용 : " + inNum);
			System.out.println("내부 클래스의 정적 변수 사용 : " + SInNum);
			System.out.println("외부 클래스의 정적 변수 사용 : " + sNum);
		}
		
		static void sInTest() {
			//num += 10;	// 외부 클래스의 인스턴스 변수 사용 불가
			//inNum += 10;  // 내부 클래스의 인스턴스 변수 사용 불가
			System.out.println("외부 클래스의 정적 변수 사용 : " + sNum);
			System.out.println("외부 클래스의 정적 변수 사용 : " + SInNum);
		}
	}
}


public class InnerClass {
	
	public static void main(String[] args) {
		// 외부 클래스를 생성하지 않고 정적 내부 클래스 생성
		OutClass.InstaticClass sInClass = new OutClass.InstaticClass();
		sInClass.inTest();
		System.out.println();
		
		// 정적 내부 클래스의 정적 메서드 호출
		OutClass.InstaticClass.sInTest();
	}
}
