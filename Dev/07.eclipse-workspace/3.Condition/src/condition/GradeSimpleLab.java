package condition;

public class GradeSimpleLab {

	public static void main(String[] args) {
		// 학생 시험 점수
		String studectName = "이순신";
		int mathScore = 85;
		int englishScore = 92;
		int koreaScore = 78;
		
		System.out.println("=== 성적 관리 시스템 ===");
		System.out.println("학생 이름 : " + studectName);
		System.out.println("수학 점수 : " + mathScore);
		System.out.println("영어 점수 : " + englishScore);
		System.out.println("국어 점수 : " + koreaScore);
		System.out.println();
		
		// 점수 유효성 검증 (0~100점 사이)
		boolean isValidScore = true;
		
		if(mathScore < 0 || mathScore > 100) {
			System.out.println("❌ 수학 점수가 잘못되었습니다. (0~100점)");
			isValidScore = false;
		}
		if(englishScore < 0 || englishScore > 100) {
			System.out.println("❌ 영어 점수가 잘못되었습니다. (0~100점)");
			isValidScore = false;
		}
		if(koreaScore < 0 || koreaScore > 100) {
			System.out.println("❌ 국어 점수가 잘못되었습니다. (0~100점)");
			isValidScore = false;
		}
		
		// 점수가 유효하지 않으면 처리 중단
		if(!isValidScore) {
			System.out.println("점수를 다시 확인해주세요");
			return;
		}
		
		// 모든 점수가 유효한 경우
		System.out.println("모든 점수가 유효합니다. 성적을 계산합니다...\n");
		
		int totalScore = mathScore + englishScore + koreaScore;
		double average = totalScore / 3.0; // 정수 + 실수 ==> 실수
		
		System.out.println("=== 성적 계산 결과 ===");
		System.out.println("총점 : " + totalScore + "점");
		/*
		 * %d : 정수
		 * %f : 실수 (float, double) - 기본 소수점 6자리 (예 : %.1f --> 소수점 첫째자리까지의 실수)
		 * %s : 문자열
		 * %c : 문자
		 * %n : 줄바꿈
		 */
		System.out.println("평균 : " + String.format("%.1f", average) + "점");
		
		String grade = "";
		if(average >= 90) {
			grade = "A";
			System.out.println("등급 : A(우수)");
		} else if (average >= 80) {
			grade = "B";
			System.out.println("등급 : B(양호)");
		} else if (average >= 80) {
			grade = "C";
			System.out.println("등급 : C(보통)");
		} else if (average >= 80) {
			grade = "D";
			System.out.println("등급 : D(미흡)");
		} else {
			grade = "F";
			System.out.println("등급 : F(불합격)");
		}
		
		System.out.println("=== 과목별 분석 ===");
	}
}



















