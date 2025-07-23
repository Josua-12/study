package generics;

public class Powder extends Material {
	
	// @Overriding이 없어도 이름이 똑같으면 오버라이딩 된 것임
	public void doPrinting() {
		System.out.println("Powder 재료로 출력합니다.");
	}
	
	@Override
	public String toString() {
		return "재료는 Powder입니다";
	}
}
