package smartcar2;

public class AICar extends Car {

	public AICar(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startEngine() {
		System.out.println(name + " : 음성 명령으로 시동을 겁니다.");
	}

	@Override
	public void drive() {
		System.out.println(name + " : 자율주행 알고리즘에 따라 운전합니다.");
	}

	@Override
	public void stop() {
		System.out.println(name + " : 자동으로 정차 후 시동을 끕니다.");
	}

	
}
