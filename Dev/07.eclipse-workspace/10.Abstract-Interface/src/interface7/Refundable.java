package interface7;

/* 환불 가능한 객체를 나타내는 인터페이스
 * - 결제 수단별 환불 정책과 기능을 정의
 * 	- 신용카드: 환불 가능 (일반적으로 7~30일)
 * 	- 계좌일체 : 환불 가능 (은행 정책에 따라)
 * 	- 모바일 간편문자 : 제한적 (앱 정책에 따라)
 */
public interface Refundable {
	// 환불 처리 추상 메서드 (핵심 기능)
	boolean processRefund(double amount);
	
	// 환불 수수로를 계산하는 디폴트와 메서드
	// 환불 수수료 정책 : 1%
	default double calculateFee(double amount) {
		return amount * 0.01;
	}
	
	// 환불 가능 기간을 반환하는 디폴트 메서드
	default int getReturnedPeriod() {
		return 7;
	}
}
