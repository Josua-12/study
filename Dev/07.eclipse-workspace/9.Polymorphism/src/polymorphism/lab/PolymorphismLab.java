package polymorphism.lab;


// 직원 관리(급여) 시스템

// 부모 클래스
class Employee {
	protected String name;		//기본이름
	protected int baseSalary;	//기본급여
	
	public Employee(String name, int baseSalary) {
		//super();
		this.name = name;
		this.baseSalary = baseSalary;
	}
	
	// 급여 계산 메서드
	public int calculateSalary() {
		return baseSalary;			// 기본 급여 반환
	}
	
	// 직원 정보 출력 메서드
	public void printEmployeeInfo() {
		System.out.println("직원명 : " + name);
		System.out.println("직원 급여 : " + baseSalary + "만원");
	}
}

// 자식클래스
// 정규직 직원 클래스
class FullTimeEmployee extends Employee {
	private int bonus;

	public FullTimeEmployee(String name, int baseSalary, int bonus) {
		super(name, baseSalary);		// 부모 생성자 호출
		this.bonus = bonus;
	}

	@Override
	public int calculateSalary() {
		return baseSalary + bonus;		// 기본급 + 보너스
	}

	@Override
	public void printEmployeeInfo() {
		super.printEmployeeInfo();
		System.out.println("보너스 : " + bonus + "만원");
		System.out.println("총 급여 : " + calculateSalary() + "만원");
		System.out.println("고용 형태 : 정규직");
	}
}

// 파트타임 직원 클래스
class PartTimeEmployee extends Employee {
	private int workHours;	// 근무 시간
	private int hourlyRate; // 시간 당 급여
	
	public PartTimeEmployee(String name,  int hourlyRate, int workHours) {
		super(name, 0);
		this.workHours = workHours;
		this.hourlyRate = hourlyRate;
	}

	@Override
	public int calculateSalary() {
		return workHours * hourlyRate;
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("직원명 : " + name);
		System.out.println("시간 당 급여 : " + hourlyRate + "만원");
		System.out.println("근무 시간 : " + workHours + "시간");
		System.out.println("총 급여 : " + calculateSalary() + "원");
		System.out.println("고용형태 : 파트타임");
		
	}
}
	
// 프리랜서 클래스
// 속성 : 프로젝트 개수, 프로젝트 당 수수료
class freelancerEmployee extends Employee {
	private int projectCount;
	private int feePerProject;
	
	public freelancerEmployee(String name, int projectCount, int feePerProject) {
		super(name, 0);
		this.projectCount = projectCount;
		this.feePerProject = feePerProject;
	}

	@Override
	public int calculateSalary() {
		return projectCount * feePerProject;
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("직원명 : " + name);
		System.out.println("프로젝트당 수수료 : " + feePerProject + "만원");
		System.out.println("완료 프로젝트 : " + projectCount + "개");
		System.out.println("총 급여 : " + calculateSalary() + "만원");
		System.out.println("고용형태 : 프리랜서");
	}
}
	
// 인턴 직원 클래스
// 속성 : 인턴쉽 기간
// 급여 계산 : 기본급 * 0.8

class internEmployee extends Employee {
	private int internshipPeriod;

	public internEmployee(String name, int baseSalary, int internshipPeriod) {
		super(name, baseSalary);
		this.internshipPeriod = internshipPeriod;
	}

	@Override
	public int calculateSalary() {
		return (int)(baseSalary * 0.8);
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("직원명 : " + name);
		System.out.println("인턴쉽 기간 : " + internshipPeriod + "(인턴쉽 기간에는 기본 월급의 80% 지급)");
		System.out.println("총 급여 : " + calculateSalary() + "만원");
	}
}

// 계약직 직원
// 속성 : 계약 개월 수(contractMonth), 계약 완료 보너스(completionBonus)
// 급여 계산 : 기본급 + (계약 개월 수 따른 보너스)
// 					------------------
//			contractMonth = completionBonus >= 12 ? completionBonus : 0
class contractEmployee extends Employee {
	private int contractMonth;
	private int completionBonus;
	
	
	public contractEmployee(String name, int baseSalary, int contractMonth, int completionBonus) {
		super(name, baseSalary);
		this.contractMonth = contractMonth;
		this.completionBonus = completionBonus;
	}

	@Override
	public int calculateSalary() {
		return baseSalary + completionBonus;
	}

	@Override
	public void printEmployeeInfo() {
		System.out.println("직원명 : " + name);
		System.out.println("계약 개월 수 : " + contractMonth + "개월");
		System.out.println("계약 완료 보너스 : " + completionBonus + "만원");
		System.out.println("직원 급여 : " + calculateSalary() + "만원");
	}
}   
	
public class PolymorphismLab {
	
	public static void main(String[] args) {
		System.out.println("🏢 다형성 연습 - 직원 관리 시스템");
		System.out.println("==================================");
		System.out.println("📊 개별 급여 계산 테스트");
		System.out.println("====================");
		
		Employee fullTime = new FullTimeEmployee("이순신", 400, 100);
		//fullTime.printEmployeeInfo();
		Employee partTime = new PartTimeEmployee("이아르바이트", 10, 80);
		//partTime.printEmployeeInfo();
		Employee freelancer = new freelancerEmployee("박프리", 3, 200);
		//freelancer.printEmployeeInfo();
		Employee fullTime2 = new FullTimeEmployee("최매니저", 600, 200);
		//fullTime2.printEmployeeInfo();
		
		Employee[] employee = {fullTime, partTime, freelancer, fullTime2};
		
		int totalSalary = 0;
		
		for (Employee e : employee) {
			printemployee(e);
			totalSalary += e.calculateSalary(); // 💰 급여 누적
		}
		
		System.out.println("📈 급여 통계");
		System.out.println("============");
		System.out.println("총 직원 수 : " + employee.length + "명");
		System.out.println("총 급여 지출 : " + totalSalary + "만원");
	}

	private static void printemployee(Employee employee) {
		
		System.out.println("=== 급여 계산서 ===");
		employee.printEmployeeInfo();
		System.out.println("================\n");
		
	}
	
	
}
