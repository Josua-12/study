package basic2;

public class Child extends Parent {

	private String speciality;
	private boolean isActive;
	
	// 기본 생성자
	public Child() {
		super();
		this.speciality = "일반";
		this.isActive = true;
	}

	public Child(String name, int id, String speciality) {
		super(name, id); 	// 부모의 매개변수 생성자 호출
		this.speciality = speciality;
		this.isActive = true;
	}
	
	public void childMethod() {
		System.out.println("자식 클래스의 고유 메서드 호출 (childMethod())");
		System.out.println("전문 분야 : " + speciality);
		System.out.println("활성 상태 : " + (isActive ? "활성" : "비활성"));
	}
	
	@Override
	public void parentMethod() {
		System.out.println("Child에서 오버라이딩 된 parentMethod()");
		super.parentMethod(); 	// 부모의 원래 메서드 호출
		System.out.println("자식에서 추가된 속성: " + speciality + " 전문가");
	}

	@Override
	public String toString() {
		return "Child [speciality=" + speciality + ", isActive=" + isActive + ", name=" + name + ", id=" + id + "]";
	}
	
	
}
