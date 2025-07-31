package outputstream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 * 파일 복사 유틸리티
 */
public class FileCopyUtil {

	// 복사 시 사용할 버퍼 크기 (64KB)
	private static final int BUFFER_SIZE = 64 * 1024;
	
	public static void main(String[] args) {
		System.out.println("=== 파일 복사 유틸리티 ===");
		
		// 복사할 원본 파일과 대상 파일 설정
		String sourceFile = "Oh_my_cool.jpg";
		String targetFile = "Oh_my_cool2.jpg";
		
		// 파일 복사 실행
		boolean success = copyFileWithProcess(sourceFile, targetFile);
		
		if(success) {
			System.out.println("파일 복사가 완료되었습니다.");
		} else {
			System.out.println("파일 복사에 실패했습니다.");
		}
		
		System.out.println("프로그램 종료");
	}
	
	// 진행률을 표시하면서 파일을 복사하는 메서드
	private static boolean copyFileWithProcess(String sourceFile, String targetFile) {
		
		try(FileInputStream sourceStream = new FileInputStream(sourceFile);
			FileOutputStream targetStream = new FileOutputStream(targetFile)) {
			
			// 이미 열린 스트림에서 바로 사용 가능
			long fileSize = sourceStream.getChannel().size();
			
			if(fileSize == 0) {
				System.out.println("경고 : 원본 파일이 비었습니다.");
				return true;	// 빈 파일도 복사 성공으로 처리
			}
			
			System.out.printf("파일 크기 : %d 바이트 (%.2f KB)\n", fileSize, fileSize/1024.0);
			
			System.out.println("복사 시작 : ...\n");
			
			// 복사 작업에 사용할 변수들
			byte[] buffer = new byte[BUFFER_SIZE];		// 버퍼 배열
			int byteRead;			// 한 번에 읽은 바이트 수
			long totalCopied = 0;	// 지금 까지 복사한 총 바이트 수
			long lastProgressTime = System.currentTimeMillis(); // 마지막 진행률 출력 시간
			
			// 파일을 버퍼 단위로 읽어서 복사
			while((byteRead = sourceStream.read(buffer)) != -1) {
				// 읽은 데이터를 대상 파일에 쓰기
				targetStream.write(buffer, 0, byteRead);
				
				// 복사한 바이트 수 누적
				totalCopied += byteRead;
				
				// 진행률 계산 및 출력
				long currentTime = System.currentTimeMillis();	// 현재 시간 가져오기 (밀리 초)
				if(currentTime - lastProgressTime >= 1000 || totalCopied == fileSize) {
					double progressPercent = totalCopied / fileSize * 100;
					String progressBar = createProgressBar(progressPercent);
					
					// 진행률 출력
					System.out.printf("\r진행률 : [%s] %.1f%% (%d / %d bytes)", 
							progressBar, progressPercent, totalCopied, fileSize);
					
					lastProgressTime = currentTime;
				}
			}
			
			// 버퍼에 남은 데이터가 있다면 강제로 파일에 쓰기 (flush)
			targetStream.flush();
			
			System.out.println("\n복사 완료");
			return true;
			
			
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			System.out.println("\n파일을 찾을 수 없습니다 : " + e.getMessage());
			System.out.println("원본 파일이 존재하는지 확인하세요 : " + sourceFile);
			return false;
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("\n파일 복사 중 I/O 오류 발생 : " + e.getMessage());
		}
		
		return false;
	}

	// 진행률 표시하는 프로그래스 바 생성하는 메서드
	private static String createProgressBar(double percent) {
		int totalBlocks = 30;	// 프로그래스 바 총 길이 (30개 문자로 구성)
		int filledBlocks = (int)(percent / 100 * totalBlocks); // 채워야 할
		
		StringBuilder bar = new StringBuilder();
		
		// 채워진 부분 그리기
		for(int i = 0; i < filledBlocks; i++) {
			bar.append("█");	// 완료된 부분 표시
		}
		
		// 빈 부분 그리기
		for(int i = filledBlocks; i < totalBlocks; i++) {
			bar.append("▒");	// 회색 블록으로 남은 부분 표시
		}
		
		return bar.toString();
	}
}
