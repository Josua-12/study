package variable;

import java.text.NumberFormat;
import java.util.Locale;

public class TimeCalculator {

	public static void main(String[] args) {
		
		System.out.println("=== 시간 계산기 ===");
		System.out.println();
		
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
		
		int totalSeconds = 7395;  //7395초
		
		// 초를 시, 분, 초로 변환
		System.out.println("--- 시간 변환 ---");
		System.out.println("총 초 : " + numberFormat.format(totalSeconds) + "초");
		
		// 1시간 = 60분 * 60초 = 3600초
		int hour = totalSeconds / 3600;
		int remainingSeconds = totalSeconds % 3600; // 나머지 초 계산
		int minutes = remainingSeconds / 60; // 1분 = 60초
		int seconds = remainingSeconds % 60; // 나머지 초 계산
		
		System.out.println("반환 결과 : " + hour + "시간" + minutes + "분" + seconds + "초");
		System.out.println();
		
		System.out.println("--- 근무시간 계산 ---");
		int daliyWorkoutTime = 8;
		int monthlyWorkoutDay = 22;
		int hourlyRate = 15000;
		System.out.println("일일 근무시간: " + daliyWorkoutTime + "시간");
		System.out.println("월 근무일수: " + monthlyWorkoutDay + "일");
		System.out.println("시급: " + numberFormat.format(hourlyRate) + "원");
		System.out.println();
		
		int MonthlyWorkoutTime = daliyWorkoutTime * monthlyWorkoutDay;
		System.out.println("월 총 근무시간: " + MonthlyWorkoutTime + "시간");
		int DaliyRate = daliyWorkoutTime * hourlyRate;
		System.out.println("일급: " + numberFormat.format(DaliyRate) + "원");
		int MonthlyRate = DaliyRate * monthlyWorkoutDay;
		System.out.println("월급: " + numberFormat.format(MonthlyRate) + "원");
		System.out.println();
		
		System.out.println("--- 추가 정보 ---");
		int YearlyWorkoutTime = MonthlyWorkoutTime * 12;
		System.out.println("연간 총 근무시간: " + numberFormat.format(YearlyWorkoutTime) + "시간");
		int salary = MonthlyRate * 12;
		System.out.println("연봉: " + numberFormat.format(salary) + "원");
		int daytime = 60 * 60 * 24;
		System.out.println("하루는 총 " + numberFormat.format(daytime) + "초입니다.");
		int weekminute = 60 * 24 * 7;
		System.out.println("일주일은 총 " + numberFormat.format(weekminute) + "분입니다.");
	}
}
