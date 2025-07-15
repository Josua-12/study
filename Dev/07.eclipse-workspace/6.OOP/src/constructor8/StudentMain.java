package constructor8;

public class StudentMain {

	public static void main(String[] args) {
	
		//기본 생성자 사용
		Student test1 = new Student();
		test1.displayInfo();
		
		
		Student test2 = new Student("이순신", 0, "");
		test2.displayInfo();
		
		
		Student test3 = new Student("신사임당", 20, "");
		test3.displayInfo();
		
		Student test4 = new Student("류성룡", 22, "컴퓨터공학 LLM");
		test4.displayInfo();
		         
		
		System.out.println("\n=== 생성자 오버로딩 완료===");
	}
}
