package twodimensionarray;

public class TwoDimensionArray {

	public static void main(String[] args) {
		int[] oneDArray = { 85, 90, 78 };
		
		System.out.println("1차원 배열 예: ");
		System.out.println("oneDArray[0] = " + oneDArray[0]);
		System.out.println("oneDArray[1] = " + oneDArray[1]);
		System.out.println("oneDArray[2] = " + oneDArray[2]);
		System.out.println();
		
		// 2차원 배열 : 배열 안에 배열이 들어있는 구조 (표 형태)
		// 행(Row)과 열(Column)로 데이터를 저장
		/*
		 * 시각적 표현
		 * 		[열0] [열1] [열2]
		 * 		수학   영어   과학
		 * [행0] 85    90   78   <--- 1번 학생
		 * [행1] 92    88   95   <--- 2번 학생
		 * [행2] 78    85   82   <--- 3번 학생
		 * [행3] 95    92   88   <--- 4번 학생
		 * [행4] 88    79   91   <--- 5번 학생
		 */
		
		int[][] scores = {
				{85, 90, 78},  // 행 인덱스 0: 1번 학생의 수학 점수(0), 
							   // 영어(1), 과학(2)
				{92, 88, 95},  // 행 인덱스 1: 2번 학생의 수학 점수(0), 
				   			   // 영어(1), 과학(2)
				{78, 85, 82},  // 행 인덱스 2: 3번 학생의 수학 점수(0), 
	   			   			   // 영어(1), 과학(2)
				{95, 92, 88},  // 행 인덱스 3: 4번 학생의 수학 점수(0), 
	   			   			   // 영어(1), 과학(2)
				{88, 79, 91}   // 행 인덱스 4: 5번 학생의 수학 점수(0), 
	   			   			   // 영어(1), 과학(2)
		};
		
		// 2차원 배열 접근
		// 형식 : 배열명[행인덱스][열인덱스]
		System.out.println("2차원 배열 접근 예: ");
		// 첫번째 []는 행(학생), 두번째 []는 열(과목)
		System.out.println("1번 학생의 수학 점수: scores[0][0] => " + scores[0][0]); // 1번 학생의 수학 점수
		System.out.println("1번 학생의 영어 점수: scores[0][1] => " + scores[0][1]); // 1번 학생의 영어 점수
		System.out.println("1번 학생의 과학 점수: scores[0][2] => " + scores[0][2]); // 1번 학생의 과학 점수
		System.out.println();
		
		System.out.println("2번 학생의 수학 점수: scores[1][0] => " + scores[1][0]); // 2번 학생의 수학 점수
		System.out.println("2번 학생의 과학 점수: scores[1][2] => " + scores[1][2]); // 2번 학생의 과학 점수
		System.out.println();
		
		System.out.println("2번 학생의 영어 점수: scores[1][1] => " + scores[4][1]); // 5번 학생의 영어 점수
		System.out.println("\n" + "=".repeat(50));
		
		System.out.println("배열 크기 정보 : ");
		System.out.println("총 학생 수(행의 개수) : " + scores.length);
		System.out.println("총 과목 수(열의 개수) : " + scores[0].length);
		System.out.println("\n" + "=".repeat(50));
		
		// 2차원 배열 출력 (중첩 반복문)
		System.out.println("모든 학생의 성적표 : ");
		System.out.println("학생\t수학\t영어\t과학");
		System.out.println("-".repeat(32));
		
		// 외부 반복문 : 행(학생) 반복
		for(int i = 0; i < scores.length; i++) {
			System.out.print((i + 1) + "번\t"); // 학생 번호
			// 내부 반복문 : 열(과목) 반복
			for(int j =0; j < scores[i].length; j++) {
				System.out.print(scores[i][j] + "\t");
			}
			System.out.println();  // 다음 행으로 이동
		}
		System.out.println("\n" + "=".repeat(50));
		
		
		System.out.println("각 학생의 평균 점수: ");
		
		for(int i = 0; i < scores.length; i++) {
			int sum = 0;
			
			for(int j =0; j < scores[i].length; j++) {
				sum += scores[i][j];
			}
			double average = (double) sum / scores[i].length;
			System.out.printf("%d번 학생 평균 : %.1f 점 \n", i + 1, average);
		}
		System.out.println("\n" + "=".repeat(50));
	}
}
