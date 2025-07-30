package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionHandling2 {

	public static void main(String[] args) throws FileNotFoundException {
		
		FileInputStream fis = new FileInputStream("a.txt");
		
		
		System.out.println("✅ 프로그램 정상 종료");
	}
}
