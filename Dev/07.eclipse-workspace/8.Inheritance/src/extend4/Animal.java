package extend4;

/* 
 * 부모 클래스 
 * 	- 모든 동물이 공통으로 가지는 속성과 행동을 정의
 */
public class Animal {

	protected String name;
	protected int age;
	
	public Animal(String name, int age) {
		//super();
		this.name = name;
		this.age = age;
		System.out.println("Animal 생성자 호출 : " + name);
	}
	
	
	public void eat() {
		System.out.println(name + "이(가) 먹습니다.");
	}
	
	public void sleep() {
		System.out.println(name + "이(가) 잠을 잡니다.");
	}
	
	public void makeSound() {
		System.out.println(name + "이(가) 소리를 냅니다.");
	}
	
	public void showInfo() {
		System.out.println("=== 동물 정보 ===");
		System.out.println("이름 : " + name);
		System.out.println("나이 : " + age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		if(age >= 0) {
			this.age = age;
		}
	}
	
	
	
}
