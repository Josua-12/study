package staticvar3;

public class Student {
	public static int serialNum = 2000;
	private int studentId;
	private String studentName;
	private int grade;
	private String address;
	
	public Student() {
		serialNum++;				// 학생이 생성될 때마다 넘버가 증가
		studentId = serialNum;		// 위에서 증가된 값을 학번 인스턴스 변수에 부여
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}