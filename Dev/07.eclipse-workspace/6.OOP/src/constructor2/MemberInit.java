package constructor2;

public class MemberInit {
	String name;
	int age;
	int grade;
	
	void initMember(String name, int age, int grade) {
		this.name = name;		// 오른쪽의 name은 매개변수 값
		this.age = age;			// this.은 인스턴스 자신의 참조값을 뜻함
		this.grade = grade;		// this를 사용해서 인스턴스 멤버 변수에 접근함
	}
}
