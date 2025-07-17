package extend3;

public class CarMain {
	
	public static void main(String[] args) {
		ElectricCar electricCar = new ElectricCar();
		electricCar.move();
		
		PickupCar pickupCar = new PickupCar();
		pickupCar.move();
	}
}
