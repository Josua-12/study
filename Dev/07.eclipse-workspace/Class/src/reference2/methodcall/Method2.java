package reference2.methodcall;

public class Method2 {

	public static void main(String[] args) {
		Student student1 = createStudent("이순신2", 15, 90);
		Student student2 = createStudent("신사임당2", 16, 80);
		
		printStudent(student1);
		printStudent(student2);
	}

	public static void printStudent(Student student1) {
		System.out.println("이름 : " + student1.name + ", 나이 : " + student1.age + 
				   ", 성적 : " + student1.grade);
	}

	public static Student createStudent(String name, int age, int grade) {
		
		Student student = new Student();
		student.name = name;
		student.age = age;
		student.grade = grade;
		
		return student;
	}
}
