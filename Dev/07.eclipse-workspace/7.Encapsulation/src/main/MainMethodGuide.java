package main;

public class MainMethodGuide {

	// 클래스 변수 - 모든 인스턴스가 공유 
	private static String applictionName = "관리 시스템";
	private static int userCount = 0;
	// 인스턴스 변수 - 각 객체마다 고유한 값을 가짐
	private String userName;
	
	public static void main(String[] args) {
		staticReason();
		commandLineArguement(args);
	}

	private static void commandLineArguement(String[] args) {
		// 명령행 인수(args) 활용
		System.out.println("2. 명령행 인수(args) 활용: ");
		System.out.println("  배열 길이: " + args.length);
		
		// 명령행 인수가 없는 경우
		if(args.length == 0) {
			System.out.println("  전달된 인수가 없습니다.");
			System.out.println("  사용법 : java MainMethodGuide <모드> <포트> <환경>");
			System.out.println("  사용예 : java MainMethodGuide server 8080 production");
			return;
		}
		
		// 명령행 인수 처리 (패턴화)
		String mode = args.length > 0 ? args[0] : "default";  // 첫 번째 인수
		int port = args.length > 1 ? Integer.parseInt(args[1]) : 8080;  // 두 번째 인수
		String environment = args.length > 2 ? args[2] : "development";  // 세 번째 인수
		
		System.out.println("  실행 모드 : " + mode);
		System.out.println("  포트 번호 : " + port);
		System.out.println("  환경 설정 : " + environment);
		
		// 인수에 따른 분기 처리 - 환경별, 모드별 실행을 구분함
		switch(mode.toLowerCase()) {
			case "server" :
				System.out.println("  -> 서버 모드로 실행합니다.");
				System.out.println("    * 웹 서버 시작 준비 중...(포트: " + port + ")");
				System.out.println("    * " +environment+ " 환경 설정 로드");
				System.out.println("    * API 엔드포인트 등록");
				System.out.println("    * 데이터베이스 커넥션 풀 초기화");
				if("production".equals(environment)) {
					System.out.println("    * 운영 환경: HTTPS 인증서 로드");
					System.out.println("    * 모니터링 시스템 연결");
					System.out.println("    * 로드 밸런서 헬스 체크 활성화");
				} else {
					System.out.println("     * 개발 환경: XXX 활성화");
					System.out.println("     * 디버그 모드 활성화");
				}
				System.out.println("     * 서버 시작 완료!");
				break;
				
				
		}
	}

	private static void staticReason() {
		System.out.println("main() 메서드가 static인 이유:");
		System.out.println(" - JVM이 클래스 로딩 시 객체 생성 없이 바로 호출해야 함");
		System.out.println(" - 프로그램 시작점이므로 인스턴스화 과정이 불필요함");
		
		// static 메서드에서는 static 변수만 직접 접근 가능 (인스턴스 변수 불가능)
		System.out.println("현재 애플리케이션 : " + MainMethodGuide.applictionName);
		System.out.println("사용자 수 : " + MainMethodGuide.userCount + "\n");
		
		// 인스턴스 변수에 접근하려면 객체를 생성해야 함
		MainMethodGuide instance = new MainMethodGuide();
		instance.userName = "이순신";
		System.out.println("인스턴스 변수 접근 : " + instance.userName + "\n");
	}
}
