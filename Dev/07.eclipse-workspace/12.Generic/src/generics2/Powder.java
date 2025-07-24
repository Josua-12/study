package generics2;

/*
 * 파우더 재료 클래스
 * 	- 금속 분말, 세라믹 분말 등에 사용
 */
public class Powder implements Material{
	private String name;		 // 재료명
	private double price;		 // 가격
	private String color;		 // 색상
	private double meshSize;	 // 입자 크기(메시)
	
	public Powder(String name, double price, String color, double meshSize) {
		//super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.meshSize = meshSize;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void printInfo() {
		System.out.printf("파우더 재료 - 이름: %s, 가격: %.2f원, 색상: %s, 입자 크기: %.1f메시\n", 
				                           name, price, color, meshSize);
	}

	
}
