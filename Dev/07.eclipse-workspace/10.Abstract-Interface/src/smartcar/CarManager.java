package smartcar;

import java.util.ArrayList;
import java.util.List;

/* 
 * 차량을 등록하고 운행하는 클래스임
 */
public class CarManager {
	
	// 차량 목록을 저장하는 리스트
	private List<Car> carList;
	
	public CarManager() {
		carList = new ArrayList<>();
	}
	
	// 차량 추가
	public void registerCar(Car car) {
		carList.add(car);
		System.out.println(car.getName() + "차량이 등록되었습니다.");
	}
	
	// 차량 운행
	public void runAllCars() {
		System.out.println("\n[차량 운행 시작]");
		for(Car car : carList) {
			car.startEngine();	// 시동
			car.drive();		// 운전
			car.fuelAmount();
			car.stop();			// 정지
			System.out.println();
		} 
		
	}
}
