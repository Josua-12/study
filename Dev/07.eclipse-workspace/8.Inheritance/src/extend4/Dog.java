package extend4;

public class Dog extends Animal {
	private String breed;

	public Dog(String name, int age) {
		super(name, age);		// 부모 생성자 호출
		// TODO Auto-generated constructor stub
	}

	public Dog(String name, int age, String breed) {
		super(name, age);  // 부모 생성자 호출
		this.breed = breed;
		System.out.println("Dog 생성자 호출 : " + breed + "품종");
	}
	
	public void bark() {
		System.out.println(name + "이(가) 멍멍멍!");
	}
	
	public void wag() {
		System.out.println(name + "이(가) 꼬리를 흔듭니다!");
	}
}
