package map2;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
	
	private String employeeId;	// 사번(primary key)
	private String name;
	private String department;	// 부서
	private String position;	// 직급
	private int salary;			// 급여
	private LocalDate hireDate;	// 입사일
	private String email;		// 이메일
	
	
	public Employee(String employeeId, String name, String department) {
		//super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
	}


	public Employee(String employeeId, String name, String department, String position, int salary,
			String email) {
		//super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.email = email;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public String getName() {
		return name;
	}


	public String getDepartment() {
		return department;
	}


	public String getPosition() {
		return position;
	}


	public int getSalary() {
		return salary;
	}


	public LocalDate getHireDate() {
		return hireDate;
	}


	public String getEmail() {
		return email;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	@Override
	public String toString() {
		return String.format("사번 : %s | 이름 : %s | 부서 : %s | 직급 : %s | 급여 : %d원 | 입사일 : %s", 
								employeeId, name, department, position !=null ? position : "미지정",
								salary, hireDate);
	}


	/*
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		// 같은 메모리 주소를 가리키면 당연히 같은 객체
		if(this == obj) return true;
		
		// null 체크
		if(obj == null) return false;
		
		// 클래스 타입 체크
		if(getClass() != obj.getClass()) return false;
		
		// 타입 캐스팅 (다운 캐스팅)
		Employee employee = (Employee)obj;
		
		
		return Objects.equals(employeeId, employee.employeeId);
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
	
}
