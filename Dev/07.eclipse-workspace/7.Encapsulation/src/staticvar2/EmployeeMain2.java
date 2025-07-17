package staticvar2;

public class EmployeeMain2 {
	public static void main(String[] args) {
		System.out.println(Employee.getSerialNum());
		Employee.setSerialNum(1004);
		System.out.println(Employee.getSerialNum());
	}
}