package reference2;

public class MethodChange2 {

	public static void main(String[] args) {
		Data dataA = new Data();
		dataA.value = 10;
		System.out.println("메서드 호출 전 : dataA.value = " + dataA.value);
		changeReference(dataA.value);
		System.out.println("메서드 호출 후 : dataA.value = " + dataA.value);
	}

	public static void changeReference(int value) {
		String name;
		int age;
		int grade;
	}	
}
