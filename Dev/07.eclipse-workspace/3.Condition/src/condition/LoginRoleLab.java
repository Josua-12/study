package condition;
/*
 * 간단한 로그인 검증 시스템 
 * 사용자 인증 로직
 * if-else 조건문을 활용한 입력값 검증
 * 역할 : 관리자, 사용자, 게스트
 */
public class LoginRoleLab {

	public static void main(String[] args) {
		// 사용자가 입력한 로그인 정보
		String inputId = "admin";
		String inputPassword = "0703";
		
		// 시스템에 저장된 실제 로그인 정보
		String correctId = "admin";
		String correctPassword = "0703";
		String loginRole = "게스트";
		
		System.out.println("=== 로그인 시스템 ===");
		System.out.println("입력된 아이디 : " + inputId);
		System.out.println("입력된 비밀번호 : " + "*".repeat(inputPassword.length()));
		System.out.println("입력된 역할 : " + loginRole);
		
		String loginStatus = "";
		
		if (inputId.isEmpty()) {
            loginStatus = "EMPTY_ID";
        } else if (inputPassword.isEmpty()) {
            loginStatus = "EMPTY_PW";
        } else if (!inputId.equals(correctId)) {
            loginStatus = "INVALID_ID";
        } else if (!inputPassword.equals(correctPassword)) {
            loginStatus = "INVALID_PW";
        } else {
            loginStatus = "SUCCESS";
        }
		
		switch (loginStatus) {
        case "EMPTY_ID":
            System.out.println("✖️ 로그인 실패 : 아이디를 입력해주세요.");
            break;
        case "EMPTY_PW":
            System.out.println("✖️ 로그인 실패 : 비밀번호를 입력해주세요.");
            break;
        case "INVALID_ID":
            System.out.println("✖️ 로그인 실패 : 아이디가 올바르지 않습니다.");
            break;
        case "INVALID_PW":
            System.out.println("✖️ 로그인 실패 : 비밀번호가 올바르지 않습니다.");
            break;
        case "SUCCESS":
            System.out.println();
            System.out.println("✅ 로그인 성공!");
            System.out.println("환영합니다. " + loginRole + "님");
            System.out.println("게스트 페이지로 이동합니다.");
            break;
		} 
	}
}
