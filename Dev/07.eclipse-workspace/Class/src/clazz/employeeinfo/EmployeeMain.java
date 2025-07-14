package clazz.employeeinfo;

public class EmployeeMain {

	public static void main(String[] args) {
		//직원 배열 생성
		Employee[] employees = new Employee[3];
		
		// 직원 정보 생성
		employees[0] = createEmployee("이개발", 1001, 45000000, 8.5, true, 'A');
		employees[1] = createEmployee("유마케팅", 1002, 40000000, 7.8, true, 'B');
		employees[2] = createEmployee("신영업", 1003, 38000000, 9.2, false, 'C');
		
		// 모든 직원 정보 출력
		printEmployees(employees);
		
		// 전체 급여 총합
		int totalSalary = getTotalSalary(employees);
		System.out.println("전체 급여 현황: " + totalSalary +"원");
		
		// 평균 성과 점수 계산
		double averageScore = getAveragePerformanceScore(employees);
		System.out.println("평균 성과 점수 : " +averageScore+ "점");
	}

	// 평균 성과 점수
	public static double getAveragePerformanceScore(Employee[] employees) {
		double totalScore = 0.0;
		for(Employee employee : employees) {
			totalScore += employee.performanceScore;
		}
		
		return totalScore / employees.length;
	}

	// 전체 급여 총합 계산
	public static int getTotalSalary(Employee[] employees) {
		int totalSalary = 0;
		for(Employee employee : employees) {
			totalSalary += employee.salary;
		}
		
		return totalSalary;
	}

	// 모든 직원 정보 출력 메서드
	public static void printEmployees(Employee[] employees) {
		System.out.println("=== 직원 정보 출력 ===");
		for(Employee employee : employees) {
			System.out.print("이름: " + employee.name);
			System.out.print(", 사번: " + employee.employeeId);
			System.out.print(", 급여: " + employee.salary + "원");
			System.out.print(", 성과점수: " + employee.performanceScore + "점");
			System.out.print(", 고용형태: " + (employee.isFullTime ? "정규직" : "비정규직"));
			System.out.print(", 부서: " + getDepartmentName(employee.department));
			System.out.println();
		}
		
	}

	public static String getDepartmentName(char deptCode) {
		switch(deptCode) {
			case 'A':		
				return "개발팀";
			case 'B':
				return "마케팅팀";
			case 'C':
				return "영업팀";
			default :
				return "미지정";	
		}
	}

	// 직원 객체 생성 --> 참조형 객체 반환
	public static Employee createEmployee(String name, int employeeId, int salary, 
					double performanceScore, boolean isFullTime, char department) {
		Employee employee = new Employee();
		employee.name = name;
		employee.employeeId = employeeId;
		employee.salary = salary;
		employee.performanceScore = performanceScore;
		employee.isFullTime = isFullTime;
		employee.department = department;
		
		return employee;
	}
}
