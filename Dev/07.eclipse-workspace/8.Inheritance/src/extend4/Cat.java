package extend4;

public class Cat extends Animal{

	private boolean isIndoor;	// 실내 고양이 여부

	public Cat(String name, int age, boolean isIndoor) {
		super(name, age);		// 부모 생성자 호출
		this.isIndoor = isIndoor;
		System.out.println("Cat 생성자 호출 : " + (isIndoor ? "실내" : "야외" ) + "고양이");
	}
	
	public void meow() {
		System.out.println(name + "이(가) 야옹~!");
	}
	
	public void grooming() {
		System.out.println(name + "이(가) 그루밍합니다.");
	}

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.println("실내 고양이 : " + (isIndoor ? "예" : "아니오"));
	}
	
	
	
}
