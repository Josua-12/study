package staticvar2;

public class EmployeeMain {
	public static void main(String[] args) {
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");
		
		Employee employeeRyu = new Employee();
		employeeRyu.setEmployeeName("류성룡");

		System.out.println(employeeLee.getEmployeeName() + ", " + employeeLee.getEmployeeId());
		System.out.println(employeeRyu.getEmployeeName() + ", " + employeeRyu.getEmployeeId());
		System.out.println(Employee.serialNum);
		System.out.println(Math.PI);
	}
}