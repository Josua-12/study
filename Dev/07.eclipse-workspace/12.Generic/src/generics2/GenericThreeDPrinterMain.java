package generics2;

public class GenericThreeDPrinterMain {

	public static void main(String[] args) {
		System.out.println("1. 재료 객체 생성");
		Plastic pla = new Plastic("PLA 필라멘트", 25000, "빨강", 1.75, 180);
		Plastic abs = new Plastic("ABS 필라멘트", 30000, "파랑", 1.75, 220);
		
		Powder titanum = new Powder("티타늄 파우더", 150000, "회색", 45.0);
		Powder steel = new Powder("스테인리스 파우더", 80000, "은색", 50.0);
		
		Resin standardResin = new Resin("표준 레진", 35000, "투명", 8, "405nm");
		Resin toughResin = new Resin("강인성 레진", 45000, "검정", 12, "385nm");
		
		System.out.println("✅ 6개의 재료 객체가 생성되었습니다. \n");
		
		System.out.println("2. 제네릭 프린터 생성");
		GenericThreeDPrinter<Plastic> fdmPrinter = 
				new GenericThreeDPrinter<Plastic>("FDM-2000");
		GenericThreeDPrinter<Powder> slsPrinter = 
				new GenericThreeDPrinter<Powder>("SLS-Pro");
		GenericThreeDPrinter<Resin> slaPrinter = 
				new GenericThreeDPrinter<Resin>("SLA-Ultra");
		
		fdmPrinter.setMaterial(pla); 			// 올바른 타입
		//fdmPrinter.setMaterial(titanimu); 	// 타입 불일치
		slsPrinter.setMaterial(titanum);
		slaPrinter.setMaterial(standardResin);
		
		System.out.println();
		
		System.out.println("3. 출력 작업 시뮬레이션");
		fdmPrinter.startPrinting("미니어쳐 피규어");
		slsPrinter.startPrinting("금속 기어");
		slaPrinter.startPrinting("정밀 부품");
		
		System.out.println();
		
		System.out.println("4. 출력 완료");
		fdmPrinter.finishPrinting();
		slsPrinter.finishPrinting();
		slaPrinter.finishPrinting();
		
	}
}
