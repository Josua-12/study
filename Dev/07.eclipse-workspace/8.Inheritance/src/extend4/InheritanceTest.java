package extend4;

public class InheritanceTest {

	public static void main(String[] args) {
		testObjectCreate();
		testInheritedMethod();
	}

	private static void testInheritedMethod() {
		// TODO Auto-generated method stub
		
	}

	private static void testObjectCreate() {
		System.out.println(" ===1. 객체 생성 ===");
		System.out.println("Dog 객체 생성");
		Dog dog = new Dog("백구", 3, "진돗개");
		
		System.out.println("\nCat 객체 생성");
		Cat cat = new Cat("나비", 2, true);
		cat.showInfo();
		
		System.out.println("\n부모 Animal 객체 생성");
		Animal animal = new Animal("동물", 5);
	}
}
