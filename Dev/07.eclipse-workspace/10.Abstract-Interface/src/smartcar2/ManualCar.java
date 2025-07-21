package smartcar2;

public class ManualCar extends Car {

	public ManualCar(String name) {
		super(name);
	}

	@Override
	public void startEngine() {
		System.out.println(name + " : 시동을 키로 돌려서 겁니다.");
		
	}

	@Override
	public void drive() {
		System.out.println(name + " : 운전자가 핸들과 기어를 직접 조작합니다.");
	}

	@Override
	public void stop() {
		System.out.println(name + " : 브레이크를 밟고 시동을 끕니다.");
	}
	
	
}
