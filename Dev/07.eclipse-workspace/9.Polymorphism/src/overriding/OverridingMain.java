package overriding;

public class OverridingMain {

	public static void main(String[] args) {
		
		// 자식 변수가 자식 인스턴스 참조
		Child child = new Child();
		child.method();
		
		// 부모 변수가 부모 인스턴스 참조
		Parent parent = new Parent();
		parent.method();
		
		// 다형적 참조 (부모 변수가 자식 인스턴스 참조)
		Parent poly = new Child();
		System.out.println("vaule = " + poly.value);	// 변수는 오버라이딩과 상관없음
		poly.method(); // Parent와 Child에 method라는 똑같은 함수가 있는데,
					   // 오버라이딩을 하면 오버라이딩된 메서드가 우선권을 가져서 Child.method()가 나옴
	}
}
