package list2;

public class Student {

	private String name;
	private int age;
	private String major;
	private double gpa; 	// 학점 평균 (Grade Point
	public Student(String name, int age, String major, double gpa) {
		//super();
		this.name = name;
		this.age = age;
		this.major = major;
		this.gpa = gpa;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public String getMajor() {
		return major;
	}
	public double getGpa() {
		return gpa;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	@Override
	public String toString() {
		//return "Student {name=" + name + ", age = " + age + ", major: " +major + ", gpa= "+ gpa + "]";
		return String.format("Student {name = '%s', age = %d, major = %s, gpa =  , %.1f}", name, age, major, gpa);
	}
	
	
	
}
