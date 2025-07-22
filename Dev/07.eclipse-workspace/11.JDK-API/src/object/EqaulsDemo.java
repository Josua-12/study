package object;

class Student {
	int studentId;
	String studentName;
	
	public Student(int studentId, String studentName) {
		//super();
		this.studentId = studentId;
		this.studentName = studentName;
	}
	
	@Override
	public String toString() {
		return studentId + ", " + studentName;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Student) {
			Student std = (Student)obj;
			if(this.studentId == std.studentId)  // 학생의 학번이 같으면 true 반환
				return true;
			else
				return false;
		}
		return false;
	}

	@Override
	public int hashCode() {
		
		return studentId;	// 해시 코드값으로 학번을 반환하도록 메서드 재정의
	}
	
	
}

public class EqaulsDemo {

	public static void main(String[] args) {
		Student studentLee = new Student(100, "이순신");
		Student StudentLee2 = studentLee;		// 주소 복사
		
		Student studentShin = new Student(100, "이순신");
		
		if(studentLee == StudentLee2) {
			System.out.println("studentLee와 StudentLee2의 주소는 같습니다.");
		}
		else {
			System.out.println("studentLee와 StudentLee2의 주소는 다릅니다.");
		}
		
		if(studentLee.equals(StudentLee2)) {
			System.out.println("studentLee와 StudentLee2는 동일합니다.");
		}
		else {
			System.out.println("studentLee와 StudentLee2는 동일하지 않습니다.");
		}
		System.out.println();
		
		if(studentLee == studentShin) {
			System.out.println("studentLee와 studentShin의 주소는 같습니다.");
		}
		else {
			System.out.println("studentLee와 studentShin의 주소는 다릅니다.");
		}
		if(studentLee.equals(studentShin)) {
			System.out.println("studentLee와 studentShin는 동일합니다.");
		}
		else {
			System.out.println("studentLee와 studentShin는 동일하지 않습니다.");
		}
		
		System.out.println();
		
		// 두 학생 객체는 논리적으로 같기 때문에 같은 해시 코드 값을 반환
		System.out.println("studentLee의 hashCode : " + studentLee.hashCode());
		System.out.println("studentLee의 hashCode : " + studentShin.hashCode());
		
		System.out.println("studentLee의 실제 주소값 : " + System.identityHashCode(studentLee));
		System.out.println("studentLee의 실제 주소값 : " + System.identityHashCode(studentShin));
	}
}
