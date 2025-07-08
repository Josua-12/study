
package loop;

public class NestedLoopLab {
	
	public static void main(String[] args) {
		System.out.println("Lab1. 시간표 생성 시스템");
		System.out.println("9시부터 18시까지, 월요일부터 금요일까지의 시간표 생성하기");
		System.out.println();
		
		// 요일 배열
		String[] days = {"월", "화", "수", "목", "금"};
		
		System.out.printf("%-8s", "시간\\요일"); // 첫 번째 셀
		for(int day = 0; day < days.length; day++) {
			System.out.printf("%-8s", days[day]); // 요일 헤더 셀 출력
		}	
		System.out.println(); // 개행
		
		for(int hour = 9; hour <18; hour++) {
			System.out.printf("%-8s", hour + "시");
			
			for(int day = 0; day < days.length; day++) { // 각 요일에 대해 반복
				if((hour + day) % 3 == 0) {
					System.out.printf("%-8s", "회의");
				} else {
					System.out.printf("%-8s", "자유");
				}
			}
			System.out.println();
		}
		
		System.out.println("\n" + "=".repeat(50) + "\n");
		
		// lab2. 좌석 예약 현황 
		seatReservation();
		
		System.out.println("\n" + "=".repeat(50) + "\n");
		
		// lab3. 성적 처리 시스템
		gradeSystem();
	}

	private static void gradeSystem() {
		System.out.println("lab3. 성적 처리 시스템");
		System.out.println("3개 과목, 5명 학생의 성적을 처리합니다.");
		System.out.println();
		
		// 과목명 배열
		String[] subjects = { "수학", "영어", "과학" };
		
		// 5명 학생의 성적 데이터
		int[][] scores = {
				{85, 	90, 	78},  
				{92, 	88, 	95},  
				{78, 	85, 	82},  
				{95,	92, 	88},  
				{88, 	79, 	91}
		}; 
		
		// 성적표 헤더 출력
		System.out.print("학생\t");
		for(int subject = 0; subject < subjects.length; subject++) {
			System.out.print(subjects[subject] + "\t");
			
		}
		System.out.println("평균");  // 평균 점수 헤더
		
		// 각 학생의 성적 및 평균 출력
		for(int student = 0; student < scores.length; student++) {
			System.out.print((student + 1) + "번\t"); // 학생 번호 출력
			
			int sum = 0;  // 해당 학생의 점수 합계 저장
			for(int subject=0; subject < subjects.length; subject++) {
				System.out.print(scores[student][subject] + "\t"); // 각 과목 점수
				sum += scores[student][subject];  // 점수 합계
			}
			
			// 평균 계산 및 출력 (소수점 1째자리까지)
			double average = (double) sum / subjects.length; // 평균 계산
			
			System.out.printf("%.1f\n", average);  // 소수점 첫째자리까지 출력
			
		}
		
		System.out.println();
		
		// 과목별 평균 계산
		System.out.println("과목별 평균 : ");
		
		for(int subject = 0; subject < subjects.length; subject++) {
			System.out.print(subjects[subject] + " : ");
			
			int subsum = 0; // 해당 과목의 점수 합계
			for(int student = 0; student < scores.length; student++) {
				subsum += scores[student][subject];
			}
			
			double subAverage = (double) subsum / scores.length; // 평균 계산
			
			System.out.printf("%.1f점\n", subAverage);
			//System.out.printf("%s : %.1f점 \n", subjects[subject], subsum);
		}
	}

	private static void seatReservation() {
		System.out.println("lab2. 좌석 예약 현황 시스템");
		System.out.println("8행 10열의 좌석 현황을 표시합니다. (O : 예약 가능, X : 예약 완료)");
		System.out.println();
		
		// 좌석 배치 정보
		int rows = 8;
		int cols = 10;
		int o = 0;
		int x = 0;
		
		System.out.printf("%3s", ""); // 첫 번째 셀
			for(int col = 1; col <= cols; col++) {
				System.out.printf("%3s", col); // 요일 헤더 셀 출력
			}	
		System.out.println(); // 개행
		
		for(int row = 1; row <= rows; row++) {
			System.out.printf("%3s", row + ":");
			
			for(int col = 1; col <= cols; col++) { // 각 요일에 대해 반복
				if((row+col) % 3 == 0 ) {  // 
	                System.out.printf("%3s", "X");
	                x++;
	            } 
				else {
	                System.out.printf("%3s", "O");
	                o++;
				}	
			}
			System.out.println();
		}
		System.out.println("\n총 좌석 수 : " + (rows * cols));
		System.out.println("예약된 좌석 : " + x);
		System.out.println("예약 가능 : " + o);
	}
	
}
