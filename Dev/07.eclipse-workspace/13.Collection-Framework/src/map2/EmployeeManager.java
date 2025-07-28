package map2;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.HashMap;


public class EmployeeManager {

	private HashMap<String, Employee> employees;
	
	// HashMap 초기화
	public EmployeeManager() {
		employees = new HashMap<>();
	}
	
	// 직원 추가 메서드
	public boolean addEmployee(Employee employee) {
		// containsKey()로 중복 사번 체크
		if(employees.containsKey(employee.getEmployeeId())) {
			System.out.println("⚠️ 이미 존재하는 사번입니다." + employee.getEmployeeId());
			return false;
		}
		
		// put()로 직원 추가
		employees.put(employee.getEmployeeId(), employee);
		System.out.println("✅ 직원이 추가되었습니다 : " + employee.getEmployeeId());
		return true;
	}
	
	// 직원 삭제 메서드 (remove())
	public Employee removeEmployee(String employeeId) {
		Employee removedEmployee = employees.remove(employeeId);
		
		if(removedEmployee != null) {
			System.out.println("✅ 직원이 삭제되었습니다 : " + removedEmployee.getName());
		} else {
			System.out.println("⚠️ 해당 사번의 직원을 찾을 수 없습니다 : " + employeeId);
		}
		
		return removedEmployee;
	}
	
	// 직원 이름으로 검색 (부분 일치 get())
	public List<Employee> findEmployeeByName(String name) {
		List<Employee> result = new ArrayList<>();
		
		for(Employee employee : employees.values()) {
			if(employee.getName().contains(name)) { // 부분 일치 검색
				result.add(employee);
			}
		}
		return result;
	}
	
	// 사번으로 직원 검색 
	public Employee findEmployeeById(String employeeId) {
		return employees.get(employeeId);		// get() 메서드로 직원 조회
		
	}
	
	// 직원 정보 수정 - 새로운 직급, 새로운 급여.. 
	public boolean updateEmployee(String employeeId, String position, int salary) {
		Employee employee = employees.get(employeeId);
		
		if(employee != null) {
			employee.setPosition(position);
			employee.setSalary(salary);
			System.out.println("✅ 직원 정보가 수정되었습니다 : " + employee.getName());
			return true;
		} else {
			System.out.println("⚠️ 해당 사번의 직원을 찾을 수 없습니다 : " + employeeId);
			return false;
		}
	}
	
	// 모든 직원 정보 출력 (정렬 - 오름차순)
	public void showAllEmployee() {
		if(employees.isEmpty()) {
			System.out.println("등록된 직원이 없습니다.");
			return;
		}
		
		System.out.println("=== 전체 직원 목록 ===");
		
		// entrySet()을 사용하여 키-값 쌍으로 순회하여 사번순으로 정렬
		employees.entrySet().stream().sorted(Map.Entry.comparingByKey())
					.forEach(entry -> System.out.println(entry.getValue())); // stream은 컬렉션을 return해줌
		
		System.out.println("총 직원 수 : " + employees.size() + "명\n");
	}
	
	// 부서별 직원 조회 (Stream API 활용)
	public List<Employee> getEmployeeByDepartment(String department) {
		return employees.values().stream()		// Stream 객체 생성
				.filter(emp -> emp.getDepartment().equals(department)) // 부서필터링
				.collect(Collectors.toList());
	}
	
	// 전체 직원 수 조회
	public int getTotalEmployeeCount() {
		return employees.size();
	}
	
	// HashMap 비어있는지 확인
	public boolean isEmpty() {
		return employees.isEmpty();
	}
	
	// 모든 직원 데이터 삭제
	public void clearAllEmployees() {
		employees.clear();  // 모든 데이터 삭제
		System.out.println("✅ 모든 직원 데이터가 삭제되었습니다.");
	}
}





















