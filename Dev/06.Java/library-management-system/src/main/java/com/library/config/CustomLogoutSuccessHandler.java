package com.library.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
    로그아웃 성공 핸들러
        - 로그아웃 성공 후 처리 로직
        - 성공 로직
        - 리다이렉트 처리
 */

@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    /*
        로그아웃 성공 후 실행되는 메서드
            - 실행시점
                - CustomLogoutHandler 실행 완료 후
                - 세션이 무효화 된 후
                - Spring Security가 자동 호출
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        log.info("=== 로그아웃 성공 ===");

        if (authentication != null) {
            log.info("로그아웃한 사용자: {}", authentication.getName());
        }

        log.info("로그인 페이지로 리다이렉트 (성공 메시지 포함)");
        log.info("==========================================");

        response.sendRedirect("/");

    }
}
