package constructor8;

public class Student {
	String name;
	int age;
	String major;
	
	// 기본 생성자 - 모든 필드를 기본값으로 초기화
	Student() {
		this("", 0, ""); // 다른 생성자 호출
	}

	// 이름만 받는 생성자
	Student(String name) {
		this(name, 0, ""); // 
	}
	
	// 이름과 나이를 받는 생성자
	Student(String name, int age) {
		//super();
		this(name, age, "");
	}
	
	// 모든 필드를 매개변수로 받는 생성자
	Student(String name, int age, String major) {
		//super();
		this.name = name;
		this.age = age;
		this.major = major;
	}

	public void displayInfo() {
		System.out.println("이름: " + name + ", 나이: " + age + ", 전공: " + major);
	}
	
}
