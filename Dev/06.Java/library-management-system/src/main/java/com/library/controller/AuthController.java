package com.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {
    /*
        로그인 페이지 처리 단계:
            - 1) 클라이언트 정보 수집 및 로깅
              2) 현재 인증 상태 확인
              3) URL 매개변수 기반 상태 메시지 처리
              4) 로그인 폼
     */
    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            @RequestParam(value = "registered", required = false) String registered,
                            @RequestParam(value = "message", required = false) String message,
                            Model model,
                            HttpServletRequest request) {
        log.info("=================================================================");
        log.info("                       🔏 로그인 페이지 접근 ");
        log.info("=================================================================");

        // 1) 클라이언트 정보 수집 및 로깅
        log.info("클라이언트 정보 수집 및 분석");

        String userAgent = request.getHeader("User-Agent");
        String referer = request.getHeader("Referer");

        log.info(" 클라이언트 접근 정보 : ");
        log.info("      - Referer: {}", referer);
        log.info("      - 접근 시간: {}", new java.util.Date());
        log.info("      - 요청 URI: {}", request.getRequestURI());
        log.info("      - 쿼리 스트링: {}", request.getQueryString());

        // 2) 현재 인증 상태 확인

        // 3) URL 매개변수 기반 상태 메시지 처리

        // 4) 세션 정보 확인

        return "auth/login";
    }

    // 회원 가입 페이지
    @GetMapping("/register")
    public String registerForm(Model model) {

        return "auth/register";
    }
}




















