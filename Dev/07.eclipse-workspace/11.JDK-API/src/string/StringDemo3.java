package string;

public class StringDemo3 {

	public static void main(String[] args) {
		String javaStr = new String("java");
		System.out.println("javaStr 주소값 : " + System.identityHashCode(javaStr));
		
		StringBuilder buffer = new StringBuilder(javaStr);
		System.out.println(buffer);
		System.out.println("buffer 주소값 : " + System.identityHashCode(buffer));
		
		buffer.append(" and");
		buffer.append(" android");
		buffer.append(" kotlin programming !!");
		System.out.println(buffer);
		System.out.println("buffer 주소값2 : " + System.identityHashCode(buffer));
	}
}
