package basic2;

/*
 * 부모 클래스 - 공통 기능을 정의하는 기본 클래스 역할
 */
public class Parent {

	protected String name;
	protected int id;
	
	public Parent() {
		this.name = "Parent";
		this.id = 0;
	}

	public Parent(String name, int id) {
		//super(); // 자바의 오브젝트
		this.name = name;
		this.id = id;
	}
	
	// 부모 클래스의 메서드
	public void parentMethod() {
		System.out.println("부모 클래스의 메서드 호출(parentMethod)");
		System.out.println("이름 : " + name + ", ID : " + id);
	}

	@Override
	public String toString() {
		return "Parent [name=" + name + ", id=" + id + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
