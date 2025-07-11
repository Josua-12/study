package clazz.emploeeinfo;

import clazz.emploeeinfo.Employee;


public class EmployeeMain {
	public static void main(String[] args) {
		//여러 상품의 주문 정보를 담는 배열 생성
		Employee[] employee = new Employee[3];
		System.out.println("=== 직원 정보 목록 ===");
		
		employee[0] = createEmployee("이개발", 1001, 4500000, 8.5, "정규직", "개발팀");
		employee[1] = createEmployee("유마케팅", 1002, 4000000, 7.8, "정규직", "마케팅팀");
		employee[2] = createEmployee("신영업", 1003, 3800000, 9.2, "비정규직", "영업팀");
		
		printEmployee(employee);
		
		int totalSalary = getTotalSalary(employee);
		double averageScore = getAverageScore(employee);
		System.out.println("전체 급여 총합: " + totalSalary +"원");
		System.out.printf("평균 성과 점수: %.1f점\n", averageScore / employee.length);
	} 

	private static double getAverageScore(Employee[] employee) {
		double totalScore = 0;
		for(Employee emp : employee) {
			totalScore += emp.score;
		}
		return totalScore;
	}

	public static int getTotalSalary(Employee[] employee) {
		int totalSalary = 0;
		for(Employee emp : employee) {
			totalSalary += emp.salary;
		}
		return totalSalary;
	}


	public static void printEmployee(Employee[] employee) {
		for(Employee emp : employee) {
			System.out.println("이름: " + emp.name + ", 사번: " + emp.number + 
							   ", 급여: " + emp.salary + "원" + ", 성과점수: " + emp.score + "점" +
							   ", 고용형태: " + emp.employeeType + ", 부서: " + emp.department);
		}
		
	}

	public static Employee createEmployee(String name, int number, int salary, double score, String employeeType, String department) {
		Employee emp = new Employee();
		emp.name = name;
		emp.number = number;
		emp.salary = salary;
		emp.score = score;
		emp.employeeType = employeeType;
		emp.department = department;
		
		return emp;
	
		
	}

	
}
