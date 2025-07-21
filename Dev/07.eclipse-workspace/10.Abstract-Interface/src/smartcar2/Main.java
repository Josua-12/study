package smartcar2;

public class Main {

	public static void main(String[] args) {
		CarManager manager = new CarManager();
		Car car1 = new ManualCar("제네시스80");
		Car car2 = new AICar("Model 3 Zero");
		Car car3 = new HybridCar("액티언 하이브리드");
		
		manager.registerCar(car1);
		manager.registerCar(car2);
		manager.registerCar(car3);
		
		manager.runAllCars();
	}
}
