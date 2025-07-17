package staticvar3;

public class StudentMain {
	public static void main(String[] args) {
		Student studentShin = new Student();
		studentShin.setStudentName("신사임당");
		System.out.println(Student.serialNum);
		System.out.println(studentShin.getStudentName() + " 학번: " + studentShin.getStudentId());
		
		Student studentLee = new Student();
		studentLee.setStudentName("이성계");
		System.out.println(Student.serialNum);
		System.out.println(studentLee.getStudentName() + "학번: " + studentLee.getStudentId());
	}
}