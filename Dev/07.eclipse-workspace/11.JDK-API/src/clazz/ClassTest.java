package clazz;

public class ClassTest {

	public static void main(String[] args) throws ClassNotFoundException {
		Person person = new Person();
		Class pClass = person.getClass();	// Object의 getClass() 사용
		System.out.println(pClass.getName());
		
		Class pClass2 = Person.class;		// 직접 class 파일 대입
		System.out.println(pClass2.getName());
		
		Class pClass3 = Class.forName("clazz.Person");
		System.out.println(pClass3.getName());
	}
}
