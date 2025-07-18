package basic;

public class CastingMain {
	
	public static void main(String[] args) {
		// 부모 타입 변수가 자식 인스턴스 참조(다형적 참조)
		Parent poly = new Child();  // 업 캐스팅(자동 형변환)
		//poly.childMethod();  // 자식의 기능은 지금 상태에선 호출 불가. 컴파일 오류
		
		// 다운 캐스팅 (부모 타입 => 자식 타입)
		Child child = (Child)poly;	// 타입을 지정하면 참조 대상을 특정 타입으로 변경
		child.childMethod();  
	}
}
