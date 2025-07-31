package inputstream;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest3 {

	public static void main(String[] args) {
		
		try(FileInputStream fis = new FileInputStream("input2.txt")) {
			// 한 번에 읽을 바이트 수를 정의하는 버퍼
			byte[] bs = new byte[10];
			
			int i; // 실제로 읽은 바이트 수를 저장			
			int j; // for문 반복 카운터
			
			// 파일 끝까지 반복하여 읽기 (read()가 -1을 반환하면 파일 끝)
			while((i = fis.read(bs)) != -1) {
				// 실제 읽은 바이트만큼 문자로 변환하여 출력
				for(j = 0; j < i; j++) {
					System.out.print((char)bs[j]);
				}
				System.out.println(" : " + j + "바이트 읽음");
			}
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("파일 읽기 오류 : " + e.getMessage());
		}
		
		System.out.println("end");
	}
}
