package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginDTO {

    //로그인에 필요한 기본 정보
    private String email;
    private String password;
    private boolean rememberMe;     // 자동 로그인 여부

    //간단한 유효성 검증
    public boolean isValid() {
        // 이메일이 비어있지 않고 @ 포함
        if(email == null || email.isEmpty() || !email.contains("@")) {
            return false;
        }

        // 비밀번호 비어있지 않고 최소 6자 이상
        if(password == null || password.length() < 6) {
            return false;
        }

        return true;
    }
}
