package com.library.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Value("dev")
    private String activeProfile;

    @GetMapping({"/", "/home"})
    public String home(Model model, HttpServletRequest request) {
        log.info("홈페이지 접근 [Profile: {}]", activeProfile);

        try {
            // 현재 URI 정보 추가
            model.addAttribute("currentUri", request.getRequestURI());

            // 페이지 정보 설정
            model.addAttribute("pageTitle", "홈");

            // 통계 데이터 (실제 환경에서는 service에서 가져옴)
            model.addAttribute("totalBooks", 12547);
            model.addAttribute("totalMembers", 3241);
            model.addAttribute("monthlyRentals", 1896);
            model.addAttribute("avgRating", "4.8");

            // 개발환경에서만 프로필 정보 표시
            if ("dev".equals(activeProfile)) {
                model.addAttribute("activeProfile", activeProfile);
                log.debug("개발 모드 - 프로필 정보 표시: {}", activeProfile);
            }

            // 성공 메시지
            if (log.isDebugEnabled()) {
                model.addAttribute("infoMessage", "도서관 시스템에 오신 것을 환영합니다.");
            }

        } catch (Exception e) {
            log.error("홈 페이지 데이터 로딩 실패: {} ", e.getMessage());
            // 에러가 발생해도 기본값으로 설정
            model.addAttribute("totalBooks", 0);
            model.addAttribute("totalMembers", 0);
            model.addAttribute("monthlyRentals", 0);
            model.addAttribute("avgRating", "0.0");
            model.addAttribute("errorMessage", "일부 데이터를 불러오는 중 문제가 발생했습니다.");
        }

        return "home";
    }
}













