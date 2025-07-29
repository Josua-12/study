package lambda2;

public class StringConcatMain {
	
	public static void main(String[] args) {
		String s1 = "Good Afternoon";
		String s2 = "Choongang!";
		
		// 클래스에서 인터페이스 구현
		StringConcat concat = new StringConcatImpl();
		concat.makeString(s1, s2);
		
		// 람다식으로 인터페이스 구현
		StringConcat concat2 = (s, v) -> System.out.println(s + ", " + v);
		concat2.makeString(s1, s2);
		
		// 익명 객체를 생성하는 람다식
		StringConcat concat3 = new StringConcat() {
			
			@Override
			public void makeString(String s1, String s2) {
				System.out.println(s1 + ", " + s2);
			}
		};
		
		concat3.makeString(s1, s2);
	}
}
