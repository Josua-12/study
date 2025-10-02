package com.library.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/*
    로그인 실패 핸들러
        - 로그인 실패 후 처리 로직
        - 실패 로깅
        - 에러 페이지로 리다이렉트
 */
@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("email");

        log.info("===로그인 실패===");
        log.info("사용자: {}", username);
        log.info("실패 사유: {}", exception.getMessage());
        log.info("IP 주소: {}", request.getRemoteAddr());
        log.info("===============");

        //로그인 페이지로 리다이렉트 (에러 파라미터 포함)
        response.sendRedirect("/auth/login?error=credentials");
    }
}
