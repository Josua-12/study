package generics2;

/*
 * 플라스틱 재료 클래스
 * 	- ABS, PLA, PETG 등 필라멘트
 */
public class Plastic implements Material {
	private String name;		 // 재료명
	private double price;		 // 가격
	private String color;		 // 색상
	private double diameter;	 // 필라멘트 직경(mm)
	private int meltingPoint;	 // 융점(섭씨)
	
	public Plastic(String name, double price, String color, double diameter, int meltingPoint) {
		//super();
		this.name = name;
		this.price = price;
		this.color = color;
		this.diameter = diameter;
		this.meltingPoint = meltingPoint;
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
		System.out.printf("파우더 재료 - 이름: %s, 가격: %.2f원, 색상: %s, 직경: %.1fmm, 융점: %d℃\n", 
                name, price, color, diameter, meltingPoint);
	}

	
}
