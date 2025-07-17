package staticvar;

public class EmployeeMain {
	public static void main(String[] args) {
		Employee employeeLee = new Employee();
		employeeLee.setEmployeeName("이순신");
		System.out.println(employeeLee.serialNum);
		
		Employee employeeRyu = new Employee();
		employeeRyu.setEmployeeName("류성룡");
		employeeRyu.serialNum++;
		//System.out.println(employeeLee.serialNum);
		System.out.println(employeeRyu.serialNum);
	}
}