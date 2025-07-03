package variable;

import java.text.NumberFormat;
import java.util.Locale;

public class TimeCalculator_wt {

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
		
		// 작업 관련 정보
		int workHours = 8;
		int workDays = 22;
		int hourlyWage = 15000;
	
		System.out.println("=== 근무시간 계산 ===");
		System.out.println("일일 근무시간: " + workHours + "시간");
		System.out.println("월 근무일수: " + workDays + "일");
		System.out.println("시급: " + numberFormat.format(hourlyWage) + "원");
		System.out.println();
		
		// 월 총 근무시간
		int monthlyWorkHours = workDays * workHours;
		System.out.println("월 총 근무시간: " + monthlyWorkHours + "시간");
		
		// 일급
		int daliyWage = workHours * hourlyWage;
		System.out.println("일급: " + numberFormat.format(daliyWage) + "원");
		
		// 월급
		int monthlyWage = daliyWage * workDays;
		System.out.println("월급: " + numberFormat.format(monthlyWage) + "원");
		System.out.println();
		
		System.out.println("--- 추가 정보 ----");
		
		// 연간 총 근무 시간
		int yearlyWorkHours = monthlyWorkHours * 12;
		System.out.println("연간 총 근무 시간: " + numberFormat.format(yearlyWorkHours) + "시간");
		
		// 연봉
		int yearlySalary = monthlyWage * 12;
		System.out.println("연봉: " + numberFormat.format(yearlySalary) + "원");
		
		// 하루는 총 몇 초입니까?
		int second = 60 * 60 * 24;
		System.out.println("하루는 총 " + numberFormat.format(second) + "초 입니다.");
		
		// 일주일은 총 몇 분입니까?
		int minute = 60 * 24 * 7;
		System.out.println("일주일은 총 " + numberFormat.format(minute) + "분 입니다.");
	}
}
