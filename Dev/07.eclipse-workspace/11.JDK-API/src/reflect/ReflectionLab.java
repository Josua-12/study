package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Student {
	private String name;
	private int age;
	private String major;
	
	public Student() {
		this.name = "익명";
		this.age = 100;
		this.major = "LLM";
	}

	public Student(String name, int age, String major) {
		//super();
		this.name = name;
		this.age = age;
		this.major = major;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", major=" + major + "]";
	}
	
	private void secretMethod() {
		System.out.println("이것은 비밀 메서드입니다.");
	}
}

public class ReflectionLab {

	public static void main(String[] args) {
		getClassInfo();
		
		getFieldInfo();
		
		getMethodInfo();
		
		createObject();
	}

	private static void createObject() {
		System.out.println("=== 4. 리플렉션으로 객체 만들기 ===");
		
		Class studentClass = Student.class;
		try {
			// newInstance()는 리플렉션의 대상이 되는 student의 기본 생성자를 호출함
			System.out.println("방법 1 : Class.newInstance()");
			Student student = (Student) studentClass.newInstance();
			System.out.println("결과 : " + student);
			
			System.out.println("\n방법 2 : Constructor 객체 사용");
			// 기본 생성자 선택
			Constructor defaultConstructor = studentClass.getConstructor();
			Student student2 = (Student) defaultConstructor.newInstance();
			System.out.println("기본 생성자 : " + student2);
			
			// 매개변수가 있는 생성자 선택
			Constructor paramConstructor = studentClass.getConstructor(String.class, int.class, String.class);
			Student student3 = (Student) paramConstructor.newInstance("이순신", 30, "LLM AI");
			System.out.println("매개변수가 있는 생성자 : " + student3/*.toString() 생략 가능*/);
			
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getMethodInfo() {
		System.out.println("=== 3. 메서드 정보 알아내기 ===");
		
		Class studentClass = Student.class;
		
		// 모든 메서드 가져오기
		Method[] methods = studentClass.getDeclaredMethods();
		System.out.println("🔧 Student 클래스의 모든 메서드");
		for(Method method : methods) {
			String returnType = method.getReturnType().getSimpleName();
			String methodName = method.getName();
			int paramCount = method.getParameterCount();
			
			System.out.println(" - " + returnType + " " + methodName 
								+ "() [매개변수 " + paramCount + "개]");
		}
		
		System.out.println();
		
	}

	private static void getFieldInfo() {
		System.out.println("=== 2. 필드(변수) 알아내기 ===");
		
		Class studentClass = Student.class;
		
		// 모든 필드 가져오기
		Field[] fields = studentClass.getDeclaredFields();
		System.out.println("Student 클래스의 모든 필드 : ");
		for(Field field : fields) {
			System.out.println(" - " + field.getType().getSimpleName() + " " + field.getName());
		}
		System.out.println();
	}
	

	private static void getClassInfo() {
		System.out.println("=== 1. 메서드 정보 알아내기 ===");
		
		Class studentClass1 = Student.class; // 방법 1
		
		Student student = new Student();
		Class studentClass2 = student.getClass();  // 방법 2
		
		try {
			Class studentClass3 = Class.forName("reflect.Student"); // 방법 3
			
			System.out.println("방법 1 : 클래스 이름.class");
			System.out.println("클래스 이름 : " + studentClass1.getName());
			System.out.println("간단한 클래스 이름 : " + studentClass1.getSimpleName());
			System.out.println();
			
			System.out.println("방법 2 : Class.forName()");
			System.out.println("클래스 이름 : " + studentClass2.getName());
			System.out.println();
			
			System.out.println("방법 3 : 객체.getClass()");
			System.out.println("클래스 이름 : " + studentClass3.getName());
			System.out.println("패키지 이름 : " + studentClass3.getPackageName());
			System.out.println();
			
			System.out.println("🔍 세 방법이 모두 같은 클래스인가요? " +
							  (studentClass1 == studentClass2 && studentClass2 == studentClass3));
			System.out.println();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
