package inputstream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest2 {
	
	public static void main(String[] args) {
		
		try(FileInputStream fis = new FileInputStream("input.txt")) {
			
			int i;
			// i값이 -1이 아닌 동안 read() 메서드로 한 바이트를 반복해 읽음
			while((i = fis.read()) !=-1) {
				System.out.println((char)i);
			}
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e);
		} 
		System.out.println("end");
	}
}
