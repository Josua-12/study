package variable;

public class GradeCalculator {

	public static void main(String[] args) {
		System.out.println("=== 성적 계산기 ===");
		System.out.println("학생명: 신사임당");
		System.out.println("학번: 2025001");
		System.out.println();
		int math = 85;
		int english = 92;
		int science = 78;
		int korean = 88;
		int history = 90;
		
		System.out.println("--- 과목별 점수 ---");
		System.out.println("수학: " + math + "점");
		System.out.println("영어: " + english + "점");
		System.out.println("과학: " + science + "점");
		System.out.println("국어: " + korean + "점");
		System.out.println("역사: " + history + "점");
		System.out.println();
		
		int total = math+english+science+korean+history;
		System.out.println("총점: " + total + "점");
		int avarage = total / 5;
		System.out.println("평균: " + avarage + "점");
		int maxScore = math;
		if (english > maxScore) maxScore = english;
		if (science > maxScore) maxScore = science;
		if (korean > maxScore) maxScore = korean;
		if (history > maxScore) maxScore = history;

		int minScore = math;
		if (english < minScore) minScore = english;
		if (science < minScore) minScore = science;
		if (korean < minScore) minScore = korean;
		if (history < minScore) minScore = history;

		System.out.println("최고점: " + maxScore + "점");
		System.out.println("최저점: " + minScore + "점");
		int scoreDifference = maxScore - minScore;
		System.out.println("최고 최저 점수 차이: "+ scoreDifference + "점");
		System.out.println();
		
		System.out.println("--- 등급 판정 ---");
		if (avarage >= 90) {
			System.out.println("등급: A (우수)");
		} else if (avarage >= 80) {
			System.out.println("등급: B (양호)");
		} else if (avarage >= 70) {
			System.out.println("등급: C (보통)");
		} else if (avarage >= 70) {
			System.out.println("등급: D (미흡)");
		} else {
			System.out.println("등급: F (재시험 필요)");
		}
	}
}
