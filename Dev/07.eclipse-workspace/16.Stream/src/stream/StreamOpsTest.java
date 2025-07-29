package stream;

import java.util.Arrays;
import java.util.List;
/*
 * Stream 중간 연산
 * 		- filter() : 조건에 맞는 요소만 통과
 * 		- map() : 요소를 다른 형태로 변환
 * 		- sorted() : 정렬
 */

class Employee {
	private String name;
	private int age;
	private String department;
	private int salary;
	
	public Employee(String name, int age, String department, int salary) {
		super();
		this.name = name;
		this.age = age;
		this.department = department;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public int getSalary() {
		return salary;
	}
	
	@Override
	public String toString() {
		return String.format("%s (나이 : %d, 부서 : %s, 연봉 : %d만원)", name, age, department, salary);
	}
	
	
}

public class StreamOpsTest {

	public static void main(String[] args) {
		
		System.out.println("=== Stream 중간 연산 연습 ===");
		
		List<Employee> employees = Arrays.asList(
			new Employee("김철수", 28, "개발팀", 3500),
			new Employee("이영희", 32, "마케팅팀", 4200),
			new Employee("박민수", 25, "개발팀", 3200),
			new Employee("최지혜", 29, "디자인팀", 3800),
			new Employee("김영수", 35, "개발팀", 4000),
			new Employee("이민정", 27, "마케팅팀", 3600)
		);
		
		System.out.println("=== 1. filter() : 조건 필터링 ===");
		// 개발팀 직원만 출력하시오
		employees.stream().filter(emp -> emp.getDepartment().equals("개발팀"))
				  .forEach(System.out::println);
		
		System.out.println("\n=== 2. map : 데이터 변환 ===");
		// 모든 직원의 이름만 추출하시오 (Employee => String)
		//employees.stream().map(Employee::getName)
		//		.forEach(System.out::println);
		employees.stream()
				.map(emp -> emp.getName())
				.forEach(System.out::println);
		
		System.out.println("\n메서드 래퍼런스 사용 :");
		employees.stream()
				.map(Employee::getName)
				.forEach(System.out::println);
		
		System.out.println("\n=== 3. filter() + map() ===");
		// 개발팀 직원의 이름만 출력하시오.
//		employees.stream()
//				.filter(emp -> emp.getDepartment().equals("개발팀"))
//				.map(emp -> emp.getName())
//				.forEach(System.out::println);
		employees.stream()
				.filter(emp -> emp.getDepartment().equals("개발팀"))
				.map(Employee::getName)
				.forEach(System.out::println);
		
		System.out.println("\n=== 4. map()으로 계산 ===");
		// 모든 직원의 연봉을 월급으로 변환해서 출력하시오.
		employees.stream()
				.map(emp -> emp.getName() + "의 월급 : " + (emp.getSalary() / 12) + "만원")
				.forEach(System.out::println);
		
		
	}
}
