package variable;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class LocaleFormatLab {

	public static void main(String[] args) {
		double amount = 1234567.89;
		double percentage = 0.1234;
		System.out.println(percentage);
		Date currentDate = new Date(); // 현재 날짜 객체 생성
		System.out.println(currentDate);
		
		// 한국의 숫자 표기 규칙에 맞춰 숫자를 포맷팅하는 객체
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);
		
		NumberFormat numberFormat2 = NumberFormat.getNumberInstance(Locale.US);
		
		NumberFormat numberFormat3 = NumberFormat.getNumberInstance(Locale.GERMANY);
		
		System.out.println("=== 기본 숫자 포맷 결과 (천단위 구분자) ===");
		System.out.println("한국: " + numberFormat.format(amount));
		System.out.println("미국: " + numberFormat2.format(amount));
		System.out.println("독일: " + numberFormat3.format(amount));
	}
}
