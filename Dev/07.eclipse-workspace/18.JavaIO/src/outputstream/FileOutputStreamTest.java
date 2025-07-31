package outputstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {

	public static void main(String[] args) {
		
		try(FileOutputStream fos = new FileOutputStream("output.txt", true)) {
			fos.write(65);
			fos.write(66);
			fos.write(67);
			
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		System.out.println("출력이 완료되었습니다.");
	}
}
