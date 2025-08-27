package hello.servlet.basic.request;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.LoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "loginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    /*
        1.JSON 문자열 읽기
        2.JSON => Java Object 변환
        3.유효성 검증 및 로그인 처리
        4.JSON 응답 전송
     */
    // ObjectMapper
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1) JSON 요청 데이터 읽기
        ServletInputStream inputStream = request.getInputStream();
        String requestBody =
                StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("----로그인 요청 받음-----");
        System.out.println("요청 JSON: " + requestBody);

        // 2) JSON을 Java 객체로 변환
        LoginDTO loginDTO = objectMapper.readValue(requestBody, LoginDTO.class);

        // 변환된 객체 확인
        System.out.println("이메일 : " + loginDTO.getEmail());
        System.out.println("비밀번호 : " + loginDTO.getPassword());
        System.out.println("자동 로그인 : " + loginDTO.isRememberMe());

        // 3) 유효성 검증
        if(!loginDTO.isValid()) {
            System.out.println("유효성 검증 실패!");
            sendErrorResponse(response, "이메일 또는 비밀번호 형식이 올바르지 않습니다.");
            return;
        }

        // 4) 로그인 처리 (실제로는 DB와 조회)
        boolean loginSuccess =
                checkLogin(loginDTO.getEmail(), loginDTO.getPassword());

        // 5-1) 로그인 성공 (응답)
        if(loginSuccess) {
            System.out.println("로그인 성공!!");
            sendSuccessResponse(response, loginDTO.getEmail());
        } else {        // 5-2) 로그인 실패 응답
            System.out.println("로그인 실패 - 잘못된 인증 정보");
            sendErrorResponse(response, "이메일 또는 비밀번호 형식이 올바르지 않습니다.");
        }


    }

    private void sendSuccessResponse(HttpServletResponse response, String email) throws IOException {
        // HTTP 상태 코드 설정
        response.setStatus(HttpServletResponse.SC_OK);      // 200 OK
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 응답 데이터 생성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("success", true);
        responseData.put("message", "로그인 성공");
        responseData.put("user", Map.of(
                        "email", email,
                        "name", "이순신",   //실제로는 db 에서 조회
                        "role", "USER"
        ));

        // JSON 변환 후 전송
        // Java Map 객체를 JSON 문자열로 직렬화 (Serialization)
        /*
            Java Map: {suceess=true, message=로그인성공, user={email=choongang@gmail.com, name=이순신, role=USER}}
            ||  (objectMapper가 변환)
            \/
            JSON 문자열: {"suceess"=true, "message"=로그인성공, "user"={"email"=choongang@gmail.com, "name"=이순신, "role"=USER}}
         */
        String jsonResponse = objectMapper.writeValueAsString(responseData);

        // HTTP 응답 바디에 JSON 문자열 작성
        response.getWriter().write(jsonResponse);

        System.out.println("응답 전송 : " + jsonResponse);

    }

    private boolean checkLogin(String email, String password) {
        //실제 DB에서 사용자 정보를 조회하고 비밀번호는 암호화되어 저장되어 있을것임
        return "choongang@gmail.com".equals(email)
                && "password123".equals(password);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        // HTTP 상태 코드 설정
        // 401 Unauthorized : 인증 실패 (인증정보가 잘못됨)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 응답  헤더 설정
        response.setContentType("application/json");  //MIME 타입 지정
        response.setCharacterEncoding("UTF-8");       //한글 깨짐 방지

        // 에러 응답 데이터 구조 생성
        Map<String,Object> responseData = new HashMap<>();
        responseData.put("sucess", false);      //실패 플래그
        responseData.put("message", message);   //사용자에게 보여줄 에러 메시지

        // Java Map 객체를 JSON 문자열로 직렬화
        /*
        {success=false, message=이메일 또는 비밀번호 형식이 올바르지 않습니다.}
        => {"success"=false, "message"=이메일 또는 비밀번호 형식이 올바르지 않습니다.}
         */
        String jsonResponse = objectMapper.writeValueAsString(responseData);

        // HTTP 응답 바디에 JSON 에러 메시지 전송
        response.getWriter().write(jsonResponse);
    }
}
