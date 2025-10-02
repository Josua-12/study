package com.library.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
    로그인 성공 핸들러
        - 로그인 성공 후 처리 로직
        - 성공 로깅
        - 리다이렉트 처리
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("===로그인 성공===");
        log.info("사용자: {}", authentication.getName());
        log.info("권한: {}", authentication.getAuthorities());
        log.info("IP 주소: {}", request.getRemoteAddr());
        log.info("===============");

        //홈페이지로 리다이렉트
        response.sendRedirect("/home");
    }
}
