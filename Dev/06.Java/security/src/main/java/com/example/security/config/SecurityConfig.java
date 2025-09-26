package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    Spring Security 핵심 설정 클래스
        - 애플리케이션의 보안 정책 중앙 관리
        - 인증(Authentication)과 인가(Authorization) 규칙 설정
        - 비밀번호 암호화 방식 정의
        - 보안 관련 설정
 */
@Configuration          // Spring 설정 클래스임을 명시 (Bean 정의 포함)
@EnableWebSecurity      // Spring Security 활성화
public class SecurityConfig {

    /*
        BCrypt 알고리즘 사용 이유 - 단방향 해시 함수로 복호화 불가능
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
        * Spring Secutiry 필터 체인 설정
            - HTTP 요청에 대한 보안 규칙 정의
            - 인증/인가 처리 순서 결정
            - 각종 보안 기능 활성화/비활성화
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //필수 설정
        http.authorizeHttpRequests(authz -> authz
                // /api/** 경로: 인증된 사용자만 접근 가능 (로그인 필수)
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/book/**").authenticated()
                // 그 외 모든 요청 : 인증 없이 접근 가능 (회원가입, 메인페이지 등)
                .anyRequest().permitAll())

        //인증 방식 (선택) -- 폼 로그인 설정
                .formLogin(form -> form
                        .loginPage("/ui/list")      //로그인 페이지 경로 저장
                        .loginProcessingUrl("/login") //실제 로그인 처리 url
                        .defaultSuccessUrl("/ui/list", true)  // 로그인 성공 후 이동할 기본 페이지
                )
       // 로그아웃 설정
                .logout(logout -> logout
                        .logoutUrl("/logout"));
        return http.build();
    }
}
