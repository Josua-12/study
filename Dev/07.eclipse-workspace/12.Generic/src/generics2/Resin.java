package generics2;

public class Resin implements Material {
	private String name;		 // 재료명
	private double price;		 // 가격
	private String color;		 // 색상
	private int cureTime;		 // 큐어링 시간
	private String uvType;		 // UV 타입
	
	public Resin(String name, double price, String color, int cureTime, String uvType) {
		super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.cureTime = cureTime;
		this.uvType = uvType;
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
		System.out.printf("파우더 재료 - 이름: %s, 가격: %.2f원, 색상: %s, 경화 시간: %d분, UV 타입: %s\n", 
                name, price, color, cureTime, uvType);
	}

}
