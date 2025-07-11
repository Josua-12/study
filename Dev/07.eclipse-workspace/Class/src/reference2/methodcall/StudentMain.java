package reference2.methodcall;

public class StudentMain {

	public static void main(String[] args) {
		Student student1;
		student1 = new Student();
		student1.name = "이순신";
		student1.age = 20;
		student1.grade = 90;
		
		Student student2 = new Student();
		student2.name = "신사임당";
		student2.age = 16;
		student2.grade = 86;
		
		System.out.println("이름 : " + student1.name + ", 나이 : " + student1.age + 
						   ", 성적 : " + student1.grade);
		System.out.println("이름 : " + student2.name + ", 나이 : " + student2.age + 
				   ", 성적 : " + student2.grade);
	}
}
