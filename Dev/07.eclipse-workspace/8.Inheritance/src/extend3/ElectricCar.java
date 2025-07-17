package extend3;

public class ElectricCar extends Car {
	
//	public void move() {
//		System.out.println("전기차를 더 빠르게 이동합니다.");
//	}
	
	
	public void charge() {
		System.out.println("충전합니다.");
	}

	@Override // override 하겠다는 메타정보 
	public void move() {
		System.out.println("전기차를 더 빠르게 이동합니다.");
	}
}
