package extend2;

public class CarMain {

	public static void main(String[] args) {
		PickupCar pickupCar = new PickupCar();
		pickupCar.move();
		pickupCar.pickup();
		pickupCar.openCar();
		
		System.out.println();
		
		ElectricCar electricCar = new ElectricCar();
		electricCar.move();
		electricCar.charge();
		electricCar.openCar();
		
		System.out.println();
		
		HydrogenCar hydrogenCar = new HydrogenCar();
		hydrogenCar.move();
		hydrogenCar.fillHydrogen();
		hydrogenCar.openCar();
	}
}
