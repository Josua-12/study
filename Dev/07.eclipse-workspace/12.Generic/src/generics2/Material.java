package generics2;
/*
 * 3D 프린터 재료의 기본 인터페이스
 * 	- 모든 재료가 구현해야 하는 공통 메서드 정의
 */
public interface Material {
	String getName();		// 재료명 반환
	double getPrice();		// 재료 가격 반환
	String getColor();		// 재료 색상 반환
	void printInfo();		// 재료 정보 출력
	
}
