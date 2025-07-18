package basic2;

public class CastingMain {

	public static void main(String[] args) {
		
		// 다형적 참조 형태의 객체 생성
		Parent poly = new Child("캐스팅 테스트", 100, "웹 개발");  // 0x001
		System.out.println("Parent 타입 변수로 Child 인스턴스 참조");
		System.out.println("참조 변수 poly의 실제 인스턴스 : " + poly.getClass(). getSimpleName());
		
		System.out.println();
		poly.parentMethod();  // 부모 메서드 호출 가능
		System.out.println(poly.toString());
		
		// 자식의 고유 메서드 호출 불가 (컴파일 오류)
		//poly.childMethod();
		
		// 다운캐스팅 수행 - 명시적 캐스팅
		System.out.println();
		
		Child child = (Child)poly;
		System.out.println("다운 캐스팅 완료");
		System.out.println("원래 poly 변수 : " + poly);
		System.out.println("캐스팅된 child 변수 : " + child);
		System.out.println("두 변수 모두 동일한 인스턴스를 참조 : " + (poly == child));
		
		// 다운 캐스팅 후 자식 메서드 호출
		System.out.println();
		System.out.println("다운 캐스팅 후 자식 메서드 호출");
		child.childMethod();
		
		System.out.println();
		System.out.println("다운캐스팅 활용하기");
		demoDowncastingUsage();
	}

	/*
	 * 인사관리 시스템 데모
	 */
	private static void demoDowncastingUsage() {
		
		System.out.println("--- 인사 관리 시스템 ---");
		
		// 부모 타입 배열
		Parent[] employees = {
				new Parent("정직원", 1),
				new Child("개발자", 2, "JAVA"),
				new Child("디자이너", 3, "UI/UX")
		};
		
		// 모든 직원에 대해 기본 정보 출력하기
		for(int i = 0; i < employees.length; i++) {
			System.out.println("=== " + (i + 1) + "번째 직원 정보 ===");
			employees[i].toString();
			
			// 개발자인 경우에만 작업 수행
			if(employees[i] instanceof Child) {
				System.out.println("개발자 발견했음! 특별 작업 수행하기");
				// 다운캐스팅
				Child developer = (Child)employees[i];
				developer.childMethod();	// 자식객체의 기능 수행
			}
			System.out.println();
		}
	}
}
