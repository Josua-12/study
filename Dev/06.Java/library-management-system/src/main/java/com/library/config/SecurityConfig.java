package com.library.config;

import com.library.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
    Spring Security 설정
        - 보안 필터 체인 설정
        - 인증/인가 규칙 정의
        - 로그인/로그아웃/회원가입 처리 설정
 */
@Slf4j
@Configuration
@EnableWebSecurity      // Spring Security 활성화
@EnableMethodSecurity(prePostEnabled = true)  // 메서드 레벨 보안 활성화
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationSuccessHandler successHandler;
    private final CustomAuthenticationFailureHandler failureHandler;
    //로그아웃 핸들러
    private final CustomLogoutHandler logoutHandler;
    private final CustomLogoutSuccessHandler logoutSuccessHandler;

    /*
        비밀번호 암호화기
            - BCrypt 해시 함수 사용, 단방향 암호화 (복호화 불가능)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("PasswordEncoder Bean 생성 - BCryptPasswordEncoder");
        return new BCryptPasswordEncoder();
    }

    /*
        인증 제공자 설정
            - userDetailsService와 PasswordEncoder 연결
            - 실제 인증 로직 수행
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        log.info("=== AuthenticationProvider 설정 시작 ===");

        DaoAuthenticationProvider authProvider =
                                    new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        log.info("1. userDetailsService 설정 완료");
        log.info("2. PasswordEncoder 설정 완료");
        log.info("=== AuthenticationProvider 설정 완료 ===");

        return authProvider;
    }
    /*
        보완 필터 체인 설정
            - URL별 접근 권한 설정
            - 로그인/로그아웃 처리 설정
            - CSRF(Cross-Site Request Forgery, 사이트 간 요청 위조), 세션 등 보안 설정
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("=== Spring Security 필터 체인 설정 시작 === ");

        http
                // AuthenticationProvider 등록 (필수)
                .authenticationProvider(authenticationProvider())

                .csrf(csrf -> {
                    csrf.disable();     //개발 단계에서는 비활성화
                    log.info("1. CSRF 보호 비활성화 (운영에서는 활성화 필요!)");
                })

                .authorizeHttpRequests(authz -> {
                    authz
                            // 누구나 접근 가능 (로그인 불필요)
                            .requestMatchers("/", "/home").permitAll()
                            .requestMatchers("/css/**", "/images/**").permitAll()
                            .requestMatchers("/auth/**", "/register", "/login").permitAll()
                            // 게시판 URL (목록/상세 조회는 모두 허용)
                            .requestMatchers("/boards/**").permitAll()

                            // 그 외 모든 요청은 인증 필요
                            .anyRequest().authenticated();
                    log.info("2. URL 권한 설정 완료");
                })
                // 폼 로그인 설정 (핵심)
                .formLogin(form -> {
                    log.info("3. 폼 로그인 설정 완료");
                    form     // 로그인 페이지 URL
                            .loginPage("/auth/login")
                            // 로그인 처리 URL (Spring Security가 자동 처리)
                            .loginProcessingUrl("/auth/login")
                            // 로그인 폼 파라미터 이름
                            .usernameParameter("email")
                            .passwordParameter("password")
                            // 성공/실패 핸들러
                            .successHandler(successHandler)
                            .failureHandler(failureHandler)

                            .permitAll();


                })
                .logout(logout -> {
                    log.info("4. 로그아웃 설정 완료");
                    logout
                            //로그아웃 요청 URL(POST 방식만 허용)
                            .logoutUrl("/auth/logout")
                            //커스텀 로그아웃 핸들러 (로그아웃 전 실행)
                            .addLogoutHandler(logoutHandler)
                            // 로그아웃 성공 핸들러 (로그아웃 후 실행)
                            .logoutSuccessHandler(logoutSuccessHandler)
                            // HTTP 세션 무효화
                            .invalidateHttpSession(true)
                            // 쿠키 삭제
                            .deleteCookies("JSESSIONID")
                            .permitAll();
                    log.info("로그아웃 설정 완료");

                })
                .exceptionHandling(exception -> {
                    log.info("5. 예외 처리 설정 완료");
                });

    log.info("=== Spring Security 필터 체인 설정 완료 ===");

    return http.build();
    }
}



















