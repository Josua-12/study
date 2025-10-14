package com.library.controller;

import com.library.dto.member.MemberRegistrationDTO;
import com.library.dto.member.MemberResponseDTO;
import com.library.service.MemberService;
import com.library.util.MaskingUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

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
        // 로그인 실패 시 에러 메시지
        if (error != null) {
            log.warn("로그인 실패 - 에러 파라미터 감지");
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            model.addAttribute("messageType", "login_error");
        }

        // 회원 가입 완료 후 리다이렉트된 경우
        if (registered != null) {
            log.info("회원 가입 완료 후 로그인 페이지 접근");
            model.addAttribute("message", "회원가입이 완료되었습니다. 로그인해 주세요.");
            model.addAttribute("messageType", "register_success");
        }

        // 4) 세션 정보 확인
        String sessionId = request.getSession().getId();
        log.info("세션 정보: ID={}, 신규여부={}", sessionId,
                                                request.getSession().isNew());

        // 5) 로그아웃 완료 메시지
        if (logout != null) {
            model.addAttribute("message", "성공적으로 로그아웃되었습니다.");
            log.info("로그아웃 성공 -- 메시지 표시");
        }

        return "auth/login";
    }

    // 회원 가입 페이지
    @GetMapping("/register")
    public String registerForm(Model model) {
        log.info("=== 회원가입 페이지 접근 ===");
        log.info("새로운 회원 가입 시도 - 폼 페이지 랜더링");

        /*
            빈 MemberRegistrationDTO 객체를 모델에 추가
            => 이를 통해 폼 필드와 객체 속성이 바인딩됨
                th:field="*{필드명}"
         */
        model.addAttribute("memberRegistrationDTO", new MemberRegistrationDTO());

        return "auth/register";
    }


    /*회원 가입 처리*/
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("memberRegistrationDTO") MemberRegistrationDTO registrationDTO,
            BindingResult bindingResult,
            Model model) {

        String maskedEmail = MaskingUtils.maskEmail(registrationDTO.getEmail());
        log.info("=== 회원가입 처리 시작: {} === ", maskedEmail);
        log.info("회원가입 데이터 수신 완료 - 유효성 검사 진행");

        if (bindingResult.hasErrors()) {
            log.error("❌ 유효성 검사 실패 - 총 {}개 오류 발견", bindingResult.getErrorCount());
            bindingResult.getAllErrors().forEach(error ->
                    log.error("\t|__검증 오류 : {}", error.getDefaultMessage())
            );
            // 첫 번째 오류 메세지를 사용자에게 표시
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", errorMessage);
            model.addAttribute("errorType", "validation_failed");
            log.error("❌ 유효성 검사 실패로 회원가입 폼 재표시");
            return "auth/register";
        }
        // 2. 비밀번호 확인 검증
        if (!registrationDTO.isPasswordMatching()) {
            log.error("❌ 비밀번호 불일치 - 사용자 : {}", maskedEmail);
            log.error("\t|__보안 체크 : 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            // 첫 번째 오류 메세지를 사용자에게 표시
            String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            model.addAttribute("error", errorMessage);
            model.addAttribute("errorType", "validation_failed");
            log.error("❌ 유효성 검사 실패로 회원가입 폼 재표시");
        }
        // 3. 이메일 중복 체크
        if (memberService.existsByEmail(registrationDTO.getEmail())) {
            log.error("❌ 이메일 중복 시도 - {} ", maskedEmail);
            log.error("  |__ 보안 알림 : 기존 회원 이메일로 가입 시도");

            model.addAttribute("error", "이미 사용 중인 이메일입니다.");
            model.addAttribute("errorType", "email_duplicate");

            return "auth/register";
        }

        // 4. 회원 가입 처리
        try {
            log.info("✅ 모든 유효성 검사 통과 - 회원 정보 저장 시작");
            //회원 등록 처리 (DTO 사용)
            MemberResponseDTO savedMember = memberService.register(registrationDTO);

            // 5. 가입 성공 처리
            log.info("회원 가입 웹 처리 성공! 회원 ID: {} ({})",
                    savedMember.getId(), maskedEmail);
            log.info("✅ 로그인 페이지로 리다이렉트 - 첫 로그인 유도");

            return "redirect:/auth/login?registered=true";

        } catch (Exception e) {
            // 6. 시스템 오류 처리
            log.error("회원 가입 시스템 오류 발생!");
            log.error("   |__ 오류 타입: {}", e.getClass().getSimpleName());
            log.error("   |-- 오류 메시지: {}", e.getMessage());
            log.error("   |-- 사용자: {}", maskedEmail);

            model.addAttribute("error",
                    "회원가입 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
            model.addAttribute("errorType", "system_error");
        }


        return "auth/register";
    }

}




















