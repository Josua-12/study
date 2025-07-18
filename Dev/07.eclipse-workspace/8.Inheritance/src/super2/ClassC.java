package super2;

public class ClassC extends ClassB {
	// 생성자는 하나만 호출할 수 있음, 부모인 ClassB에는 기본 생성자 없음
	// 부모의 기본 생성자를 호출하는 super()를 사용할 수 없음
	public ClassC() {
		super(10, 20);		
		System.out.println("ClassC 생성자");
	}
}
