package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionHandling {

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("a.txt");
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println(e.toString());	// 예외 클래스의 toString() 메서드 호출
		}
		
		System.out.println("✅ 프로그램 정상 종료");
	}
}
