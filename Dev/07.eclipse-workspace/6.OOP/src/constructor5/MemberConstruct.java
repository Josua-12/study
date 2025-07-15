package constructor5;

public class MemberConstruct {
	String name;
	int age;
	int grade;
	
	
	public MemberConstruct(String name, int age, int grade) {
		//super();
		this.name = name;
		this.age = age;
		this.grade = grade;
	}


	public MemberConstruct(String name, int age) {
		//super();
//		this.name = name;
//		this.age = age;
//		this.grade = 50;
		// System.out.println("test"); 
		this(name, age, 50);	// 생성자 내부에서 또 다른 생성자를 호출함
	}
}

