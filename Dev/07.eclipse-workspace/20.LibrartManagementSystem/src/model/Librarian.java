package model;

/*
 * 도서관 사서를 나타내는 클래스
 * Person 클래스를 상속받음
 */
public class Librarian extends Person{
	private String department;		// 소속 부서
	private String position;		// 직책
	
	public Librarian(String id, String name, String phone, String department, String position) {
		super(id, name, phone);
		this.department = department;
		this.position = position; 
	}

	public String getDepartment() {
		return department;
	}

	public String getPosition() {
		return position;
	}
	
	@Override
	public String getRole() {
		return "사서";
	}

	
	// 사서 정보를 문자열로 반환
	@Override
	public String toString() {
		return super.toString() + String.format(", 부서 : %s, 직책 : %s", department, position);
	}

	
}








