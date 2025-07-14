package clazz.employeeinfo;

import java.util.Scanner;

public class EmployeeMain2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		//등록할 직원 수 입력 받기
		System.out.print("등록할 직원 수를 입력하세요: ");
		int employeeCount = scanner.nextInt();		
		scanner.nextLine();		// 입력 버퍼 정리 
		
		//직원 배열 생성
		Employee[] employees = new Employee[employeeCount];
		
		for(int i = 0; i < employees.length; i++) {
			System.out.println("\n" + (i + 1) + "번째 직원 정보를 입력하세요.");
			System.out.print("이름 : ");
			String name = scanner.nextLine();
			System.out.print("사번 : ");
			int empolyeeId = scanner.nextInt();
			System.out.print("급여 : ");
			int salary = scanner.nextInt();
			System.out.print("성과 점수 (0.0 ~ 10.0) : ");
			double performanceScore = scanner.nextDouble();
			System.out.print("정규직 여부 (true/false) : ");
			boolean isFullTime = scanner.nextBoolean();
			System.out.print("부서 코드 (A:개발, B:마케팅, C:영업): ");
			char department = scanner.next().charAt(0);
			scanner.nextLine();		// 입력 버퍼 정리 
			
			// 입력받은 정보로 직원 객체 생성
			employees[i] = createEmployee(name, empolyeeId, salary, 
							    performanceScore, isFullTime, department);
		}
		
		// 모든 직원 정보 출력
		printEmployees(employees);
		
		// 통계 점수 출력
		printStatistics(employees);
		
		scanner.close();
	}

	// 통계 점수 출력 메서드
	public static void printStatistics(Employee[] employees) {
		System.out.println("=== 통계 정보 ===");
		
		// 전체 급여 총합
		int totalSalary = getTotalSalary(employees);
		System.out.println("전체 급여 총합: " + totalSalary +"원");
		
		// 평균 급여 
		double averageSalary = (double)totalSalary / employees.length;
		System.out.println("평균 급여: " + (int)averageSalary +"원");
		
		// 평균 성과 점수 계산
		double averageScore = getAveragePerformanceScore(employees);
		System.out.printf("평균 성과 점수 : %.1f점\n", averageScore);
		
		// 정규직 비율 계산
		int fullTimeCount = 0;		// 정규직 수 카운트
		for (Employee emp : employees) {
			if(emp.isFullTime) {	// boolean 변수 조건 확인
				fullTimeCount++;
			}
		}
		double fullTimeRatio = 
				(double)fullTimeCount / employees.length * 100;
		System.out.printf("정규직 비율: %.1f%%\n", fullTimeRatio);
		
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
		System.out.println("=== 등록된 직원 정보 ===");
		int idx = 1;
		for(Employee employee : employees) {
			System.out.println(idx +". "+ employee.name + "(사번: " +employee.employeeId +")");
			System.out.println(", 급여: " + employee.salary + "원");
			System.out.println(", 성과점수: " + employee.performanceScore + "점");
			System.out.println(", 고용형태: " + (employee.isFullTime ? "정규직" : "비정규직"));
			System.out.println(", 부서: " + getDepartmentName(employee.department));
			System.out.println();
			idx++;
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
