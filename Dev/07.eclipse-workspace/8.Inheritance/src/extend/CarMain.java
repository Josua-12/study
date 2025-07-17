package extend;

public class CarMain {

	public static void main(String[] args) {
		PickupCar pickupCar = new PickupCar();
		pickupCar.move();
		pickupCar.pickup();
		
		ElectricCar electricCar = new ElectricCar();
		electricCar.move();
		electricCar.charge();
	}
}
