package scanner;

import java.util.Scanner;

public class ArrayScanner6 {

	public static void main(String[] args) {
		//콘솔에서 사용자 입력 받기 위한 Scanner 객체 생성
		Scanner scanner = new Scanner(System.in);
		
		//2차원 배열 선언 - 4명, 3개 과목 점수 저장
		int[][] scores = new int[4][3];
		
		//과목명 저장할 배열
		String[] subjects = {"국어", "영어", "수학"};
		
		// 4명의 학생에 대한 반복
		for(int i = 0; i < 4; i++) {
			System.out.println((i+1) + "번 학생의 성적을 입력하세요 : ");
			
			// 3개 과목에 대해 반복
			for(int j = 0; j < 3; j++) {
				System.out.print(subjects[j] + "점수 : ");
				// 2차원 배열 점수 저장
				scores[i][j] = scanner.nextInt();
			}
		}
		
		// 입력받은 점수들 기반으로 각 학생의 총점, 평균 계산
		for(int i = 0; i < 4; i++) {
			// 현재 학생의 총점 저장 변수
			int total =0;
			// 현재 학생의 3개 과목 점수 모두 더함
			for(int j = 0; j < 3; j++) {
				total += scores[i][j];
			}
			
			// 평균
			double average = total / 3.0;
			
			// 출력
			System.out.println((i + 1) + "번 학생의 총점 : " + total + ", 평균 : " + String.format("%.1f", average));
		}
	}
}
