package inputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LogFileReader {
	
	public static void main(String[] args) {
		System.out.println("=== 로그 파일 읽기 ===");
		
		String logFilePath = "server.log";
		
		try(FileInputStream fis = new FileInputStream(logFilePath)) {
			
			// 한글 처리를 위해 바이트 배열 버퍼
			byte[] buffer = new byte[8192];		// 8KB 버퍼
			int byteRead; 		// 실제로 읽은 바이트 수
			int lineNumber = 1;	// 라인 번호 카운트
			boolean isNewLine = true;		// 새 라인의 시작인지 확인
			
			System.out.println("로그 파일 내용 : ");
			System.out.println("-----------------------------------");
			
			// 파일을 버퍼 단위로 읽어서 처리 (한글깨짐 방지)
			while((byteRead = fis.read(buffer)) != -1) {
				// 읽은 바이트 배열을 UTF-8 문자열로 변환
				String content = new String(buffer, 0, byteRead, "UTF-8");
				
				// 문자열을 한 글자씩 처ㅣ
				for(int i = 0; i < content.length(); i++) {
					char ch = content.charAt(i);
					
					if(isNewLine) {
						System.out.printf("%3d:", lineNumber);
						isNewLine = false;		// 더이상 새 라인이 아님
					}
					// 문자 출력
					System.out.print(ch);
					
					// 개행 문자를 만나면 다음이 새 라인임을 표시
					if(ch == '\n') {
						isNewLine = true;
						lineNumber++;		// 라인 번호 표시
						
					}
				}
				
			}
			
			System.out.println("\n--------------------------------");
			System.out.println("총 " + lineNumber + "줄을 읽었습니다.");
			
		}  catch (IOException e) {
			//e.printStackTrace();
			// 파일을 찾을 수 없거나 읽기 오류 발생 시 처리
			System.err.println("파일 읽기 오류 : " + e.getMessage());
			System.err.println("server.log 파일이 프로젝트 루트에 있는지 확인하세요.");
		}
		
		System.out.println("프로그램 종료");
	}
}
