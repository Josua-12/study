package generics2;

public class GenericThreeDPrinter<T extends Material> {

	private T material;				// 현재 장착된 재료
	private String priterModel;		// 프린터 모델명
	private boolean isPrinting;		// 프린팅 여부
	
	public GenericThreeDPrinter(String priterModel) {
		//super();
		this.priterModel = priterModel;
		this.isPrinting = false;
	}
	
	// 재료 장착 메서드
	public void setMaterial(T material) {
		if(isPrinting) {
			System.out.println("⚠️ 출력 중에는 재료를 교체할 수 없습니다.");
			return;
		}
		
		this.material = material;
		System.out.printf("✅ %s에 재료가 장착되었습니다: %s\n", priterModel, material.getName());
	}
	
	// 재료 반환
	public T getMaterial() {
		return material;
	}
	
	// 3D 출력 시작
	public void startPrinting(String odjectName) {
		if(material == null) {
			System.out.println("❌ 재료가 장착되지 않았습니다. 먼저 재료를 장착해주세요.");
			return;
		}
		
		if(isPrinting) {
			System.out.println("⚠️ 이미 출력중입니다.");
		}
		
		isPrinting = true;
		System.out.printf("%s로 '%s' 출력을 시작합니다...\n", priterModel, odjectName);
	}
	
	// 3D 출력 완료
	public void finishPrinting() {
		if(!isPrinting) {
			System.out.println("⚠️ 현재 출력 중이 아닙니다.");
			return;
		}
		
		isPrinting = false;
		System.out.printf("✅ %s 출력이 완료되었습니다.\n", priterModel);
	}
}
