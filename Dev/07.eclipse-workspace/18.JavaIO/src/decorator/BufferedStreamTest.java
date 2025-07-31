package decorator;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamTest {

	public static void main(String[] args) {
		long millisecond = 0;
		try(FileInputStream fis = new FileInputStream("Oh_my_cool.jpg");
				FileOutputStream fos = new FileOutputStream("Oh_my_cool5.jpg");
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			
			millisecond = System.currentTimeMillis(); // 시작 시간 측정

            int i;
            while ((i = bis.read()) != -1) { // 한 바이트씩 읽어서
                bos.write(i);                // 한 바이트씩 씀
            }

            bos.flush(); // 버퍼에 남은 데이터 밀어내기

            millisecond = System.currentTimeMillis() - millisecond; // 걸린 시간 측정
            
            System.out.println("복사 시간: " + millisecond + "ms");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
