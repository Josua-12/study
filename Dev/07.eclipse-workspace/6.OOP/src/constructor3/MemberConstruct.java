package constructor3;

public class MemberConstruct {
	String name;
	int age;
	int grade;
	
	MemberConstruct(String name, int age, int grade) {
		//System.out.println("생성자 호출 name = " + name + ", age = " + age + ", grade = "
			//				+ grade);
		this.name = name;
		this.age = age;
		this.grade = grade;
	}
}
